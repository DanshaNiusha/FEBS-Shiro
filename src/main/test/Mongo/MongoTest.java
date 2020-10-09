package Mongo;

import cc.mrbird.febs.FebsShiroApplication;
import cc.mrbird.febs.article.entity.Comment;
import cc.mrbird.febs.article.service.CommentService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author liuxiaokang
 * @description mongo测试
 * @date 2020/10/9
 */
@RunWith(SpringRunner.class)
//SpringBoot的测试环境初始化，参数：启动类
@SpringBootTest(classes = FebsShiroApplication.class)
public class MongoTest {
    
    @Autowired
    private CommentService commentService;
    
    /**
     * 保存一个评论
     */
    @Test
    public void testSaveComment() {
        Comment comment = new Comment();
        comment.setArticleid("100000");
        comment.setContent("测试添加的数据");
        comment.setCreatedatetime(LocalDateTime.now());
        comment.setUserid("1003");
        comment.setNickname("凯撒大帝");
        comment.setState("1");
        comment.setLikenum(0);
        comment.setReplynum(0);
        
        commentService.saveComment(comment);
    }
    
    /**
     * 查询所有数据
     */
    @Test
    public void testFindAll() {
        List<Comment> list = commentService.findCommentList();
        System.out.println(list);
    }
    
    /**
     * 测试根据id查询
     */
    @Test
    public void testFindCommentById() {
        Comment comment = commentService.findCommentById("5d6a27b81b8d374798cf0b41");
        System.out.println(comment);
    }
}
