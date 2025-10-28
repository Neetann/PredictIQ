package com.nokia.predict.utils;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

public class TeamsNotifier {

	private static final String WEBHOOK_URL = "https://nokia.webhook.office.com/webhookb2/c5c3cf65-5c6d-4b1f-9a75-3fbffb75623e@5d471751-9675-428d-917b-70f44f9630b0/IncomingWebhook/7b9df759863949008c4712f9cf6e74ca/a0255617-e2e5-4a3b-83df-178b9b082652/V2_FvAkd3LY2TK0Wl_3dW13gXIrjyVSvkaTNMvyV1ElXI1";

	public void sendOutageNotification(String message) {
		RestTemplate restTemplate = new RestTemplate();

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);

		// Payload format for Teams webhook
		String jsonPayload = String.format("{\"text\": \"%s\"}", message);

		HttpEntity<String> entity = new HttpEntity<>(jsonPayload, headers);

		restTemplate.postForObject(WEBHOOK_URL, entity, String.class);
	}
}
