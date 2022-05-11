package com.projet.BackendPfe.services;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import java.util.zip.DataFormatException;
import java.util.zip.Deflater;
import java.util.zip.Inflater;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.projet.BackendPfe.Entity.Expert;
import com.projet.BackendPfe.repository.ExpertRepository;

@Service
public class ExpertService {
	protected String gender ;
	protected long telephone;

	
	@Autowired
	ExpertRepository expertRepository;
	

	public Expert updateImage(long id , MultipartFile file) throws IOException {
		 Expert expert = expertRepository.findById(id).get();
		expert.setImage(compressZLib(file.getBytes()));
		expertRepository.save(expert);
		return expert ; 
	}
	public void ajouterExpert( Expert  expert) throws IOException {
		 Expert expert1 = new Expert();
		 expert1.setEmail(expert.getEmail());
		 expert1.setUsername(expert.getUsername());
		 expert1.setGender(expert.getGender());
		 expert1.setTelephone(expert.getTelephone());
		 expert1.setPassword(expert.getPassword());
		 expert1.setImage(compressZLib(expert.getImage()));
		expert1.setAdmin(expert.getAdmin());
		expertRepository.save(expert1);
	}
	public static byte[] compressZLib(byte[] data) {
		Deflater deflater = new Deflater();
		deflater.setInput(data);
		deflater.finish();

		ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
		byte[] buffer = new byte[1024];
		while (!deflater.finished()) {
			int count = deflater.deflate(buffer);
			outputStream.write(buffer, 0, count);
		}
		try {
			outputStream.close();
		} catch (IOException e) {
		}
		System.out.println("Compressed Image Byte Size - " + outputStream.toByteArray().length);

		return outputStream.toByteArray();
	}

	// uncompress the image bytes before returning it to the angular application
	public static byte[] decompressZLib(byte[] data) {
		Inflater inflater = new Inflater();
		inflater.setInput(data);
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
		byte[] buffer = new byte[1024];
		try {
			while (!inflater.finished()) {
				int count = inflater.inflate(buffer);
				outputStream.write(buffer, 0, count);
			}
			outputStream.close();
		} catch (IOException ioe) {
		} catch (DataFormatException e) {
		}
		return outputStream.toByteArray();
	}
	

}
