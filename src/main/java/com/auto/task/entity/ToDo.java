package com.auto.task.entity;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * ToDo entity which defines a text, isCompleted and createdAt attributes.
 * isCompleted is a boolean true/false
 *
 */
@Entity(name = "ToDo")
@Table(name = "ToDo")
public class ToDo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private BigDecimal id;

	@Column(name = "text")
	private String text = null;

	@Column(name = "isCompleted")
	private Boolean isCompleted = null;

	@Column(name = "createdAt")
	private String createdAt = null;

	public ToDo() {
	}

	public ToDo(String text, Boolean isCompleted, String createdAt) {
		this.text = text;
		this.isCompleted = isCompleted;
		this.createdAt = createdAt;
	}

	public BigDecimal getId() {
		return id;
	}

	public void setId(BigDecimal id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Boolean getIsCompleted() {
		return isCompleted;
	}

	public void setIsCompleted(Boolean isCompleted) {
		this.isCompleted = isCompleted;
	}

	public String getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(String createdAt) {
		this.createdAt = createdAt;
	}
}
