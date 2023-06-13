package com.javaex.vo;

public class CategoryVo {

	private int cateNo;
	private String id;
	private String cateName;
	private String description;
	private String regDate;
	private int postcnt;
	
	public CategoryVo() {
		
	}
	
	public CategoryVo(String id, String cateName, String description, int postcnt) {
		this.id = id;
		this.cateName = cateName;
		this.description = description;
		this.postcnt = postcnt;
	}

	public CategoryVo(int cateNo, String id, String cateName, String description, String regDate, int postcnt) {
		this.cateNo = cateNo;
		this.id = id;
		this.cateName = cateName;
		this.description = description;
		this.regDate = regDate;
		this.postcnt = postcnt;
	}

	public int getCateNo() {
		return cateNo;
	}

	public void setCateNo(int cateNo) {
		this.cateNo = cateNo;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCateName() {
		return cateName;
	}

	public void setCateName(String cateName) {
		this.cateName = cateName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getRegDate() {
		return regDate;
	}

	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}

	public int getPostcnt() {
		return postcnt;
	}

	public void setPostcnt(int postcnt) {
		this.postcnt = postcnt;
	}

	@Override
	public String toString() {
		return "CategoryVo [cateNo=" + cateNo + ", id=" + id + ", cateName=" + cateName + ", description=" + description
				+ ", regDate=" + regDate + ", postcnt=" + postcnt + "]";
	}
	
}
