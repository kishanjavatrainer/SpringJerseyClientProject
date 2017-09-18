package com.infotech.jersey.client;

import java.util.List;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.infotech.jersey.model.Topic;

public class JerseyClient {

	public void createTopic(Topic topic) {
		Client client = null;
		try {
			client = ClientBuilder.newClient();
			WebTarget target = client.target("http://localhost:8080/topics");
			WebTarget add = target.path("create");
			Response response = add.request(MediaType.APPLICATION_JSON).post(Entity.json(topic));
			System.out.println("Response Http Status: " + response.getStatus());
			System.out.println(response.getLocation());
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (client != null) {
				client.close();
			}
		}
	}

	public void updateTopic(Topic topic) {
		Client client = null;
		try {
			client = ClientBuilder.newClient();
			WebTarget base = client.target("http://localhost:8080/topics");
			WebTarget update = base.path("/topic/update");
			Response response = update.request(MediaType.APPLICATION_JSON).put(Entity.json(topic));

			System.out.println("Response Http Status: " + response.getStatus());
			Topic topicResponse = response.readEntity(Topic.class);
			System.out.println(
					topicResponse.getTopicId() + ", " + topicResponse.getTitle() + ", " + topicResponse.getCategory());
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (client != null) {
				client.close();
			}
		}
	}

	public void getAllTopics() {
		Client client = null;
		try {
			client = ClientBuilder.newClient();
			WebTarget target = client.target("http://localhost:8080/topics");
			List<Topic> topicList = target.request(MediaType.APPLICATION_JSON).get(new GenericType<List<Topic>>() {
			});
			topicList.stream().forEach(
					topic -> System.out.println(topic.getTopicId() + ", " + topic.getTitle() + ", " + topic.getCategory()));
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			if(client != null){
				client.close();
			}
		}
	}

	public void getTopicById(int topicId) {
		Client client = null;
		try {
			client = ClientBuilder.newClient();
			WebTarget base = client.target("http://localhost:8080/topics/topicId");
			WebTarget topicById = base.path("{topicId}").resolveTemplate("topicId", topicId);
			Topic topic = topicById.request(MediaType.APPLICATION_JSON).get(Topic.class);

			System.out.println(topic.getTopicId() + ", " + topic.getTitle() + ", " + topic.getCategory());
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			if(client != null){
				client.close();
			}
		}
	}

	public void deleteTopic(int topicId) {
		Client client = null;
		try {
			client = ClientBuilder.newClient();
			WebTarget target = client.target("http://localhost:8080/topics/topicId");
			WebTarget deleteById = target.path("{topicId}").resolveTemplate("topicId", topicId);
			Response response = deleteById.request(MediaType.APPLICATION_JSON).delete();

			System.out.println("Response Http Status: " + response.getStatus());
			if (response.getStatus() == 204) {
				System.out.println("Topic deleted successfully.");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			if(client != null){
				client.close();
			}
		}
	}

	public static void main(String[] args) {
		JerseyClient jerseyClient = new JerseyClient();
		//jerseyClient.createTopic(getTopic());
		//jerseyClient.updateTopic(updateTopic());
		 jerseyClient.getAllTopics();
		// jerseyClient.getTopicById(1);
		//jerseyClient.deleteTopic(3);
	}

	private static Topic getTopic() {
		Topic createTopic = new Topic();
		createTopic.setTitle("Spring Framework");
		createTopic.setCategory("Spring AOP");
		return createTopic;
	}

	private static Topic updateTopic() {
		Topic updateTopic = new Topic();
		updateTopic.setTopicId(6);
		updateTopic.setTitle("Spring Boot");
		updateTopic.setCategory("Spring Boot Jersey ");
		return updateTopic;
	}
}
