package com.example.demo.objectstorage;

import java.io.InputStream;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.example.demo.config.NaverConfiguration;

@Service
public class NCPObjectStorageService implements ObjectStorageService {
	
	final AmazonS3 s3;
	
	@Autowired
	NaverConfiguration naverConfiguration;
	
	public NCPObjectStorageService(NaverConfiguration naverConfiguration) {
		s3 = AmazonS3ClientBuilder
				.standard()
				.withEndpointConfiguration(
						new AwsClientBuilder
							.EndpointConfiguration(naverConfiguration.getEndPoint(),
												   naverConfiguration.getRegionName())
				)
				.withCredentials(new AWSStaticCredentialsProvider(
									new BasicAWSCredentials(naverConfiguration.getAccessKey(),
															naverConfiguration.getSecretKey())
									)
				)
				.build();
	}

	@Override
	public String uploadFile(MultipartFile img) {
		System.out.println("--------uploadfile 인식 불가!!----------------------");
		System.out.println(img);
		try(InputStream inputStream = img.getInputStream()) {
			String imageFileName = UUID.randomUUID().toString();
			//String imageFileName = img.getOriginalFilename();
			
			ObjectMetadata objectMetadata = new ObjectMetadata();
			objectMetadata.setContentType(img.getContentType());
			
			PutObjectRequest putObjectRequest = 
					new PutObjectRequest(naverConfiguration.getBucketName(),
							naverConfiguration.getDirectoryPath() + imageFileName,
										 inputStream,
										 objectMetadata).withCannedAcl(CannedAccessControlList.PublicRead);
			
			s3.putObject(putObjectRequest);
			System.out.println("imageFileName : " + imageFileName);
			return imageFileName;
		}catch(Exception e) {
			throw new RuntimeException("파일 업로드 에러");
		}
		
	}

	@Override
	public void deleteFile(String imageFileName) {
		s3.deleteObject(naverConfiguration.getBucketName(), 
						naverConfiguration.getDirectoryPath()+ imageFileName);
	}

}