 package com.umar.registerform.userbean;

import java.util.Arrays;

import jakarta.persistence.*;

@Entity
@Table(name = "user_data")
public class UserData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String email;
    private String name;
    private String adhaar;
    private String city;
    private String state;

    @Lob
    @Column(name = "image", columnDefinition = "BLOB")
    private byte[] image;

    // Getters and setters, and other methods

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAdhaar() {
        return adhaar;
    }

    public void setAdhaar(String adhaar) {
        this.adhaar = adhaar;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "UserData [id=" + id + ", email=" + email + ", name=" + name + ", adhaar=" + adhaar + ", city=" + city
				+ ", state=" + state + ", image=" + Arrays.toString(image) + "]";
	}

    
}
