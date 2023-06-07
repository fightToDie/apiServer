package org.example.events;

import lombok.RequiredArgsConstructor;

import java.math.BigInteger;

import org.example.service.SequenceGenerator.SequenceGeneratorService;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.BeforeConvertEvent;
import org.springframework.stereotype.Component;

public class PostsModelListener {}

//@RequiredArgsConstructor
//@Component
//public class PostsModelListener extends AbstractMongoEventListener<Posts> {
//
//    private final SequenceGeneratorService sequenceGenerator;
//
//    @Override
//    public void onBeforeConvert(BeforeConvertEvent<Posts> event) {
////        if (event.getSource().getId() < 1) {
//    		BigInteger id = new BigInteger(String.valueOf(sequenceGenerator.generateSequence(Posts.SEQUENCE_NAME)));
//            event.getSource().setId(id);
////        }
//    }
//}
