package cc.mrbird.febs.article.dao;

import cc.mrbird.febs.article.entity.Comment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * @author liuxiaokang
 * @description 评论的持久层
 * @date 2020/10/9
 */
public interface CommentRepository extends MongoRepository<Comment, String> {
    
    /**
     * @description 根据父id，查询子评论的分页列表 方法名是固定的 可以自动解析 是实体类中的属性
     * @author liuxiaokang
     * @date 2020/10/10 9:12
     */
    Page<Comment> findByParentid(String parentid, Pageable pageable);
}
