package com.dbclm.nace.entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


/**
 * @author Srinivas
 *
 */
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name="NaceItems")
public class Item {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="orderId")
	//@NotNull
	private String orderId;
	
	@Column
	//@NotNull
	private String level;
	
	@Column
	//@NotNull
	private String code;
	
	@Column
	//@NotNull
	private String parent;
	
	@Column
	//@NotNull
	private String description;
	
	@Column
	//@NotNull
	@Lob
	private String includes;
	
	@Column(name="alsoIncludes")
	//@NotNull
	@Lob
	private String alsoIncludes;
	
	@Column
	//@NotNull
	private String rulings;
	
	@Column
	//@NotNull
	@Lob
	private String excludes;
	
	@Column
	//@NotNull
	private String reference;

	@Override
	public String toString() {
		return "Item [id=" + id + ", orderId=" + orderId + ", level=" + level + ", code=" + code + ", parent=" + parent
				+ ", description=" + description + ", includes=" + includes + ", alsoIncludes=" + alsoIncludes
				+ ", rulings=" + rulings + ", excludes=" + excludes + ", reference=" + reference + "]";
	}
	
	
}
