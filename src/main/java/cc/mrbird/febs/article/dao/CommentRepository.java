package cc.mrbird.febs.article.dao;

import cc.mrbird.febs.article.entity.Comment;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * @author liuxiaokang
 * @description 评论的持久层
 * @date 2020/10/9
 */
public interface CommentRepository extends MongoRepository<Comment,String> {

}
