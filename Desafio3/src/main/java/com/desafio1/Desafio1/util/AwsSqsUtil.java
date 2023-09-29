package com.desafio1.Desafio1.util;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import org.springframework.stereotype.Service;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSClientBuilder;
import com.amazonaws.services.sqs.model.CreateQueueRequest;
import com.amazonaws.services.sqs.model.DeleteMessageRequest;
import com.amazonaws.services.sqs.model.Message;
import com.amazonaws.services.sqs.model.ReceiveMessageRequest;
import com.amazonaws.services.sqs.model.SendMessageRequest;
import com.desafio1.Desafio1.services.Fila.FilaService;

@Service
public class AwsSqsUtil {

    @Autowired
    private FilaService FilaService;
	
    private String AWS_ACCESS_KEY;
    private String AWS_SECRET_KEY;
    private String AWS_SQS_QUEUE;
    private String AWS_SQS_QUEUE_ARN;
    private String AWS_SQS_QUEUE_URL;

    public AwsSqsUtil() {
        AWS_ACCESS_KEY = "AKIAVXQT4T777DMLKGOE";
        AWS_SECRET_KEY = "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";
        AWS_SQS_QUEUE = "Cielo-sqs";
        AWS_SQS_QUEUE_ARN = "arn:aws:sqs:us-east-1:394104971263:Cielo-sqs";
        AWS_SQS_QUEUE_URL = "https://sqs.us-east-1.amazonaws.com/394104971263/Cielo-sqs";
    }

	
	private AWSCredentials awsCredentials() {
		AWSCredentials credentials = new BasicAWSCredentials(AWS_ACCESS_KEY, AWS_SECRET_KEY);
		return credentials;
	}

	private AmazonSQS sqsClientBuilder() {
		AmazonSQS sqs = AmazonSQSClientBuilder.standard()
				.withCredentials(new AWSStaticCredentialsProvider(awsCredentials())).withRegion(Regions.SA_EAST_1)
				.build();
		return sqs;
	}

	public void createSQSQueue(String queueName) {
		AmazonSQS sqsClient = sqsClientBuilder();
		CreateQueueRequest createStandardQueueRequest = new CreateQueueRequest(queueName);
		String standardQueueUrl = sqsClient.createQueue(createStandardQueueRequest).getQueueUrl();
		System.out.println("AWS SQS Queue URL: " + standardQueueUrl);
	}

	public String produceMessageToSQS(String message) {
		AmazonSQS sqsClient = sqsClientBuilder();
		SendMessageRequest request = new SendMessageRequest().withQueueUrl(AWS_SQS_QUEUE_URL).withMessageBody(message)
				.withDelaySeconds(1);

		return sqsClient.sendMessage(request).getMessageId();
	}

	public List<Message> consumeMessageFromSQS() {
		AmazonSQS sqsClient = sqsClientBuilder();

		ReceiveMessageRequest request = new ReceiveMessageRequest(AWS_SQS_QUEUE_URL).withWaitTimeSeconds(1)
				.withMaxNumberOfMessages(10);

		List<Message> sqsMessages = sqsClient.receiveMessage(request).getMessages();
		for (Message message : sqsMessages) {
			//run process for message
			System.out.println(message.getBody());
			
			//dequeue message after using it
			//also perfect step so check if message was successfully processed
			dequeuMessageFromSQS(message);
		}
		return sqsMessages;
	}

	public void dequeuMessageFromSQS(Message message) {
		AmazonSQS sqsClient = sqsClientBuilder();

		sqsClient.deleteMessage(new DeleteMessageRequest()
				  .withQueueUrl(AWS_SQS_QUEUE_URL)
				  .withReceiptHandle(message.getReceiptHandle()));

	}
}