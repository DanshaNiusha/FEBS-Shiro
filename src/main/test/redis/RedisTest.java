package redis;

import cc.mrbird.febs.FebsShiroApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.connection.RedisZSetCommands;
import org.springframework.data.redis.connection.SortParameters;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.data.redis.core.query.SortQueryBuilder;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Map;
import java.util.Set;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = FebsShiroApplication.class)
public class RedisTest {
    
    @Autowired
    private RedisTemplate<String,Object> redisTemplate;
    
    @Test
    public void testHash() {
        HashOperations<String, String, Object> hashOps = redisTemplate.opsForHash();
        hashOps.put("user1", "name", "dansha");
        hashOps.put("user1", "sex", "man");
        hashOps.put("user1", "age", 18);
        Map<String, Object> entries = hashOps.entries("user");
        String name = (String) hashOps.get("user", "name");
        Boolean isdel = (Boolean) hashOps.get("user", "isdel");
        hashOps.delete("user", "name", "isdel");
        name = (String) hashOps.get("user", "name");
        isdel = (Boolean) hashOps.get("user", "isdel");
        System.out.println();
    }

    @Test
    public void testSet() {
        SetOperations<String, Object> setOps = redisTemplate.opsForSet();
        setOps.add("userSet","dansha1","dansha2","dansha3");
        setOps.add("dogSet","dansha1","dog1","dog2","dog3");
        Set<Object> userSet = setOps.members("userSet");
        // 取userSet独有
        Set<Object> diffSet = setOps.difference("userSet","dogSet");
        // 并集
        Set<Object> unionSet = setOps.union("userSet","dogSet");
        // 交集
        Set<Object> intersectSet = setOps.intersect("userSet","dogSet");
        System.out.println();
    }
    
    @Test
    public void testZSet() {
        ZSetOperations<String, Object> zSetOps = redisTemplate.opsForZSet();
        zSetOps.add("userZset","dansha",10);
        zSetOps.add("userZset","fish",3);
        zSetOps.add("userZset","dog",1);
        zSetOps.add("userZset","niusha",5);
        zSetOps.add("userZset","shit",-2);
        // 取分数排名前三的 asc
        Set<Object> range = zSetOps.range("userZset", 0, 3);
        zSetOps.rangeByLex("userZset", RedisZSetCommands.Range.range());
        // 获取分数在0-3之间的
        Set<Object> scoreReverse = zSetOps.reverseRangeByScore("userZset", 0, 3);
        // 获取指定值的分数
        Double score = zSetOps.score("userZset", "fish");
        // 集合大小
        Long card = zSetOps.zCard("userZset");
        Long size = zSetOps.size("userZset");
        
        System.out.println();
    }
    
    @Test
    public void testSort() {
        /**
         * list排序
         */
        // ListOperations<String, Object> listOps = redisTemplate.opsForList();
        // listOps.rightPush("lista",2);
        // listOps.rightPush("lista",5);
        // listOps.rightPush("lista",4);
        // listOps.rightPush("lista",3);
        // listOps.rightPush("lista",1);
        // List<Object> lista = listOps.range("lista", 0, -1);
        // SortQueryBuilder<String> builder = SortQueryBuilder.sort("lista");
        // builder.order(SortParameters.Order.DESC);
        // List<Object> sortedList = redisTemplate.sort(builder.build()); // 54321
    
        /**
         * hash排序
         */
        SetOperations<String, Object> setOps = redisTemplate.opsForSet();
        setOps.add("userids",1,2,3);
        
        HashOperations<String, String, Object> hashOps = redisTemplate.opsForHash();
        hashOps.put("user:1", "name", "dansha");
        hashOps.put("user:1", "age", 18);
        hashOps.put("user:2", "name", "niusha");
        hashOps.put("user:2", "age", 22);
        hashOps.put("user:3", "name", "dog");
        hashOps.put("user:3", "age", 24);
        hashOps.put("user:4", "name", "cat");
        hashOps.put("user:4", "age", 17);
        SortQueryBuilder<String> hashBuilder = SortQueryBuilder.sort("userids");
        // hash的key前缀
        String prefix = "user:";
        // 根据对象哪个属性排序
        String byKey = "age";
        hashBuilder.by(prefix + "*->" + byKey); // user:*->age
        hashBuilder.order(SortParameters.Order.DESC);
        // 收集name属性
        hashBuilder.get(prefix + "*->" + "name");
        // 将排序后的集合存起来TODO 暂时不管用, 但是直接用命令可以
        hashBuilder.get("# store sortedUsers");
        hashBuilder.limit(0, 5);// 从0开始 取5个
        List<Object> sortedUserList = redisTemplate.sort(hashBuilder.build()); // dog niusha dansha
       
        
        System.out.println();
    }
}
