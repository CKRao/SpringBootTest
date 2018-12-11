package com.clarkrao.springboot;

import com.clarkrao.springboot.dao.MongoTestDao;
import com.clarkrao.springboot.entity.TestEntity;
import org.bson.Document;
import org.bson.types.ObjectId;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: ClarkRao
 * @Date: 2018/12/11 22:37
 * @Description:
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class SdnwareOdlApplicationTests {
    private static final String MONGODB_NAME = "test1";

    @Autowired
    private MongoTestDao mongoTestDao;

    @Test
    public void saveTestEntity(){
        List<Document> documents = new ArrayList<>();
        Document entry = new Document();
        entry.put("ifIndex", 2);
        entry.put("ifInOctets", 586888423);
        entry.put("ifOutOctets", 1054861105);
        entry.put("ifInUcastPkts", 24821672);
        entry.put("ifOutUcastPkts", 15946353);
        entry.put("ifSpeed", 1000000000);
        entry.put("ifMtu", 1514);
        documents.add(entry);

        TestEntity entity = new TestEntity();

        entity.setId(ObjectId.get());
        entity.setIfEntry(documents);
        entity.setIfRecvTime(LocalDateTime.now().plusHours(8));
        entity.setIfNumber(50);

        mongoTestDao.saveData(entity, MONGODB_NAME);
    }

    @Test
    public void getAllDatas(){
        List<TestEntity> testEntities = mongoTestDao.getAllData(MONGODB_NAME);

        testEntities.forEach(entity -> {
            LocalDateTime ifRecvTime = entity.getIfRecvTime();
            System.out.println(ifRecvTime + ": ");
            List<Document> ifEntry = entity.getIfEntry();
            ifEntry.forEach(entry -> {
                Integer ifIndex = entry.getInteger("ifIndex");
                Integer ifInOctets = entry.getInteger("ifInOctets");
                Integer ifOutOctets = entry.getInteger("ifOutOctets");
                System.out.println("ifIndex = " + ifIndex);
                System.out.println("ifInOctets = " + ifInOctets);
                System.out.println("ifOutOctets = " + ifOutOctets);
            });
        });
    }
}
