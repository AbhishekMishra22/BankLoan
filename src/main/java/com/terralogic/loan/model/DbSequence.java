package com.terralogic.loan.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "db_squence")

public class DbSequence {
	@Id
	private String id;
	private int seqNo;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getSeqNo() {
		return seqNo;
	}

	public void setSeqNo(int seqNo) {
		this.seqNo = seqNo;
	}

	public DbSequence(String id, int seqNo) {
		super();
		this.id = id;
		this.seqNo = seqNo;
	}

	public DbSequence() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "DbSequence [id=" + id + ", seqNo=" + seqNo + "]";
	}

}
