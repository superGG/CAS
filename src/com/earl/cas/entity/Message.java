package com.earl.cas.entity;
// Generated 2016-11-21 9:28:51 by Hibernate Tools 5.2.0.Beta1

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * Message generated by hbm2java
 */
@Entity
@Table(name = "message", catalog = "clubsystem")
public class Message implements Serializable
{
	private static final long serialVersionUID = 1L;
	
	private int id;
	private Message message;
	private User user;
	private String content;
	private Date createtime;
	private Integer good;
	private Integer bad;
	private Set<Message> messages = new HashSet<Message>(0);

	public Message() {
	}

	public Message(int id, Date createtime) {
		this.id = id;
		this.createtime = createtime;
	}

	public Message(int id, Message message, User user, String content,
			Date createtime, Integer good, Integer bad, Set<Message> messages) {
		this.id = id;
		this.message = message;
		this.user = user;
		this.content = content;
		this.createtime = createtime;
		this.good = good;
		this.bad = bad;
		this.messages = messages;
	}

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "father_id")
	public Message getMessage() {
		return this.message;
	}

	public void setMessage(Message message) {
		this.message = message;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Column(name = "content", length = 65535)
	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Transient   //设置该属性后标致该属性不持久化数据库，由数据库自己管理
	public Date getCreatetime() {
		return this.createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

	@Column(name = "good")
	public Integer getGood() {
		return this.good;
	}

	public void setGood(Integer good) {
		this.good = good;
	}

	@Column(name = "bad")
	public Integer getBad() {
		return this.bad;
	}

	public void setBad(Integer bad) {
		this.bad = bad;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "message")
	public Set<Message> getMessages() {
		return this.messages;
	}

	public void setMessages(Set<Message> messages) {
		this.messages = messages;
	}

	@Override
	public String toString() {
		return "Message [id=" + id + ", message=" + message + ", user=" + user
				+ ", content=" + content + ", createtime=" + createtime
				+ ", good=" + good + ", bad=" + bad + ", messages=" + messages
				+ "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((bad == null) ? 0 : bad.hashCode());
		result = prime * result + ((content == null) ? 0 : content.hashCode());
		result = prime * result
				+ ((createtime == null) ? 0 : createtime.hashCode());
		result = prime * result + ((good == null) ? 0 : good.hashCode());
		result = prime * result + id;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Message other = (Message) obj;
		if (bad == null) {
			if (other.bad != null)
				return false;
		} else if (!bad.equals(other.bad))
			return false;
		if (content == null) {
			if (other.content != null)
				return false;
		} else if (!content.equals(other.content))
			return false;
		if (createtime == null) {
			if (other.createtime != null)
				return false;
		} else if (!createtime.equals(other.createtime))
			return false;
		if (good == null) {
			if (other.good != null)
				return false;
		} else if (!good.equals(other.good))
			return false;
		if (id != other.id)
			return false;
		return true;
	}
}
