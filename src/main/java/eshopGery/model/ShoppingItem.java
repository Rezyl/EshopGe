package eshopGery.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

/**
 * Created with IntelliJ IDEA.
 * User: lukas
 * Date: 22.6.14
 */
@Entity
@Table
public class ShoppingItem {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long itemId;

	@Column
	private String name;

	@Column
	private String description;

	@Transient
	private String size;

	@Column
	private Integer price;

	@Column
	@Enumerated(EnumType.STRING)
	private Category category;

	@Column
	private String[] availableSizes;

	@Column
	private Integer quantity;

	@Column
	private String imageFilePath;

	@Column
	private String imageForGallery;

	public Long getItemId() {
		return itemId;
	}

	public void setItemId(Long itemId) {
		itemId = itemId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public String getImageFilePath() {
		return imageFilePath;
	}

	public void setImageFilePath(String imageFilePath) {
		this.imageFilePath = imageFilePath;
	}

	public String getImageForGallery() {
		return imageForGallery;
	}

	public void setImageForGallery(String imageForGallery) {
		this.imageForGallery = imageForGallery;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String[] getAvailableSizes() {
		return availableSizes;
	}

	public void setAvailableSizes(String[] availableSizes) {
		this.availableSizes = availableSizes;
	}

	public List<String> getAvailableSizesAsList() {
		List<String> result = new ArrayList<String>();
		if (availableSizes != null) {
			for (String availableSize : availableSizes) {
				result.add(SizeOfSock.valueOf(availableSize).getDisplayName());
			}
		}
		return result;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;

		ShoppingItem that = (ShoppingItem) o;

		if (category != that.category)
			return false;
		if (imageFilePath != null ? !imageFilePath.equals(that.imageFilePath) : that.imageFilePath != null)
			return false;
		if (!itemId.equals(that.itemId))
			return false;
		if (name != null ? !name.equals(that.name) : that.name != null)
			return false;
		if (price != null ? !price.equals(that.price) : that.price != null)
			return false;
		if (size != null ? !size.equals(that.size) : that.size != null)
			return false;

		return true;
	}

	@Override
	public int hashCode() {
		int result = itemId.hashCode();
		result = 31 * result + (name != null ? name.hashCode() : 0);
		result = 31 * result + (size != null ? size.hashCode() : 0);
		result = 31 * result + (price != null ? price.hashCode() : 0);
		result = 31 * result + (category != null ? category.hashCode() : 0);
		result = 31 * result + (imageFilePath != null ? imageFilePath.hashCode() : 0);
		return result;
	}
}
