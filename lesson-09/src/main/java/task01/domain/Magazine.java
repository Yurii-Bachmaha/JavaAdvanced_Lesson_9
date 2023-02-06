package task01.domain;

import java.util.Date;
import java.util.Objects;

public class Magazine {

	private Integer id;
	private String name;
	private String description;
	private Date publishDate;
	private Double subscribePrice;

	public Magazine(Integer id, String name, String description, Date publishDate, Double subscribePrice) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.publishDate = publishDate;
		this.subscribePrice = subscribePrice;
	}

	public Magazine(String name, String description, Date publishDate, Double subscribePrice) {
		super();
		this.name = name;
		this.description = description;
		this.publishDate = publishDate;
		this.subscribePrice = subscribePrice;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getPublishDate() {
		return publishDate;
	}

	public void setPublishDate(Date publishDate) {
		this.publishDate = publishDate;
	}

	public Double getSubscribePrice() {
		return subscribePrice;
	}

	public void setSubscribePrice(Double subscribePrice) {
		this.subscribePrice = subscribePrice;
	}

	@Override
	public int hashCode() {
		return Objects.hash(description, id, name, publishDate, subscribePrice);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Magazine other = (Magazine) obj;
		return Objects.equals(description, other.description) && Objects.equals(id, other.id)
				&& Objects.equals(name, other.name) && Objects.equals(publishDate, other.publishDate)
				&& Objects.equals(subscribePrice, other.subscribePrice);
	}

	@Override
	public String toString() {
		return "Magazine [id=" + id + ", name=" + name + ", description=" + description + ", publishDate=" + publishDate
				+ ", subscribePrice=" + subscribePrice + "]";
	}

}