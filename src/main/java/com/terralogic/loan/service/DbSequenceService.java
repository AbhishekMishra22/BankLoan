package com.terralogic.loan.service;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import com.terralogic.loan.model.DbSequence;

@Service
public class DbSequenceService {

	private MongoOperations mongoOperations;

	@Autowired
	public DbSequenceService(MongoOperations mongoOperations) {
		this.mongoOperations = mongoOperations;
	}

	public long generateDbSequence(String seqName) {

		Query querry = new Query(Criteria.where("id").is(seqName));
		Update update = new Update().inc("seqNo", 1);
		DbSequence counter = mongoOperations.findAndModify(querry, update,
				FindAndModifyOptions.options().returnNew(true).upsert(true), DbSequence.class);
		return !Objects.isNull(counter) ? counter.getSeqNo() : 1;

	}

}
