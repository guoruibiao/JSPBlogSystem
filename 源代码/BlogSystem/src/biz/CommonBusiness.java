package biz;

import java.util.ArrayList;
import java.util.Date;

import bean.Comment;
import bean.Post;
import bean.Tag;
import bean.User;
import dao.CommentDAO;
import dao.PostDAO;
import dao.PostTagDAO;
import dao.TagDAO;
import dao.UserDAO;

/**
 * 相关于常规性质的业务需求处理，用户登录注册，文章、评论、标签CRUD等。
 * 
 * @author biao
 *
 */
public class CommonBusiness {

	/**
	 * 用户操作相关DAO。
	 */
	private UserDAO userDao = new UserDAO();

	/**
	 * 文章增删改查相关DAO。
	 */
	private PostDAO postDao = new PostDAO();

	/**
	 * 评论增删改查相关处理DAO。
	 */
	private CommentDAO commentDao = new CommentDAO();

	/**
	 * 标签相关DAO。
	 */
	private TagDAO tagDao = new TagDAO();

	/**
	 * 对于多对多关系的文章与标签表的内容获取相关处理。
	 */
	private PostTagDAO postTagDao = new PostTagDAO();

	/**
	 * 从前台接收用户名、密码，通过底层后台校验判断用户身份的合法性。
	 * 
	 * @param username
	 *            用户名
	 * @param password
	 *            密码
	 * @return 用户身份合法则返回TRUE，否则返回False。
	 * @throws Exception
	 */
	public boolean login(String username, String password) throws Exception {
		return userDao.login(username, password);
	}

	/**
	 * 从前台接收表单数据，组装成一个User对象。调用底层DAO进行用户注册业务处理。
	 * 
	 * @param username
	 *            用户名，长度限制为64位。理论上来说支持Unicode字符表示。
	 * @param password
	 *            密码，严格来说不管多长的密码都会被转为32位的哈希串。
	 * @param email
	 *            邮箱， 长度限制为255位。
	 * @param sex
	 *            性别， Boolean类型，TRUE表示男生，False表示女生。
	 * @return 返回TRUE表示注册成功，否则表示注册失败。
	 * @throws Exception
	 */
	public boolean register(String username, String password, String email, boolean sex) throws Exception {
		User user = new User();
		user.setName(username);
		user.setPassword(password);
		user.setEmail(email);
		user.setSex(sex);

		return userDao.register(user);
	}

	/**
	 * 由于Post对象参数较多，这里不打算直接罗列在参数列表中而是采用了包装模式进行简化参数列表。
	 * 
	 * @param post
	 *            包含了用户待发布文章信息的Post类实例。
	 * @return 返回TRUE表明post对象满足数据库内相对应的字段要求。返回False代表有些字段不符合要求，需要进一步的处理。
	 * @throws Exception
	 */
	public boolean publishPost(Post post) throws Exception {
		return postDao.add(post);
	}

	/**
	 * 通过前台数据库中查到的文章编号来更新文章内容。
	 * 
	 * @param post_id
	 *            文章编号
	 * @param post
	 *            待更新的post新内容。
	 * @return 返回TRUE表示更新文章成功，返回False表示更新操作失败。
	 * @throws Exception
	 */
	public boolean updatePost(Integer post_id, Post post) throws Exception {
		return postDao.update(post_id, post);
	}

	/**
	 * 根据前台获取到的该文章在数据库中的编号，删除对应的文章内容。
	 * 
	 * @param post_id
	 *            文章在数据库内的编号。
	 * @return 返回TRUE表示删除成功，返回False表示删除失败。
	 * @throws Exception
	 */
	public boolean deletePost(Integer post_id) throws Exception {
		return postDao.delete(post_id);
	}

	/**
	 * 根据用户名来获取该作者发表过的所有文章。
	 * 
	 * @param username
	 *            博客名称，也可以称之为作者名。
	 * @return 返回列表对象说明该用户名下发表过文章，返回null则代表该用户还未发表过文章。
	 * @throws Exception
	 */
	public ArrayList<Post> getPosts(String username) throws Exception {

		return userDao.getPosts(username);
	}

	/**
	 * 根据博客的数据库编号获取该文章的作者信息。
	 * 
	 * @param post_id
	 *            数据库内文章的编号。
	 * @return 数据库内文章编号对应的详细作者信息。
	 * @throws Exception
	 */
	public User getAuthor(Integer post_id) throws Exception {
		return postDao.getAuthor(post_id);
	}

	/**
	 * 根据用户名获取该用户发表过的所有评论信息。
	 * 
	 * @param username
	 *            用户名
	 * @return 用户名对应的评论列表。
	 * @throws Exception
	 */
	public ArrayList<Comment> getComments(String username) throws Exception {

		return userDao.getComments(username);
	}

	/**
	 * 根据前台获取到的新建的标签名称来创建标签对象。并持久化到数据库中。
	 * 
	 * @param name
	 *            标签名称
	 * @return 返回TRUE表示新建标签成功，返回False表示新建标签失败。
	 * @throws Exception
	 */
	public boolean addTag(String name) throws Exception {
		Tag tag = new Tag();
		tag.setName(name);
		return tagDao.add(tag);
	}

	/**
	 * 根据前台传过来的标签名称删除数据库内对应的标签。
	 * 
	 * @param tagname
	 *            标签名称
	 * @return 返回TRUE表示删除标签成功，返回False表示删除标签失败。
	 * @throws Exception
	 */
	public boolean deleteTag(String tagname) throws Exception {

		return tagDao.delete(tagname);
	}

	/**
	 * 根据前台传过来的标签在数据库内的编号来删除数据库内对应的标签。
	 * 
	 * @param tag_id
	 *            标签在数据库内对应的ID编号。
	 * @return 返回TRUE表示删除标签操作成功，返回False表示删除相应标签操作失败。
	 * @throws Exception
	 */
	public boolean deleteTag(Integer tag_id) throws Exception {

		return tagDao.delete(tag_id);
	}

	/**
	 * 根据前台传过来的标签在数据库内的编号来修改相应的标签名称。
	 * 
	 * @param tag_id
	 *            标签在数据库内对应的编号。
	 * @param tagname
	 *            标签的新名字。
	 * @return 返回TRUE表示修改标签名称成功，返回False表示修改相应标签操作失败。
	 * @throws Exception
	 */
	public boolean updateTag(Integer tag_id, String tagname) throws Exception {
		Tag tag = new Tag();
		tag.setName(tagname);
		return tagDao.update(tag_id, tag);
	}

	/**
	 * 根据前台表单提交的数据组装一个合法的Comment对象，提交到数据库中。
	 * 
	 * @param content
	 *            评论详细内容。
	 * @param user_id
	 *            评论人ID。
	 * @param post_id
	 *            被评论的文章的编号。
	 * @return 返回TRUE表示评论成功，返回False表示评论失败。
	 * @throws Exception
	 */
	public boolean addComment(String content, Integer user_id, Integer post_id) throws Exception {
		Comment comment = new Comment();
		comment.setContent(content);
		comment.setCommenttime(new Date());
		comment.setUser_id(user_id);
		comment.setPost_id(post_id);

		return commentDao.add(comment);
	}

	/**
	 * 根据前台获取到的本条评论在数据库内的编号来删除此评论。
	 * 
	 * @param comment_id
	 *            评论咋数据库内对应的编号。
	 * @return 返回TRUE表示删除评论成功，返回False表示删除评论操作失败。
	 * @throws Exception
	 */
	public boolean deleteComment(Integer comment_id) throws Exception {

		return commentDao.delete(comment_id);
	}

	/**
	 * 根据前台传过来的评论的编号ID来更新评论的内容。默认来说按照第一次发表评论的时间显示。
	 * 
	 * @param comment_id
	 *            评论在数据库内的编号
	 * @param content
	 *            评论详情
	 * @return 返回TRUE表示更新评论内容成功，返回False表示更新评论操作失败。
	 * @throws Exception
	 */
	public boolean updateComment(Integer comment_id, String content) throws Exception {
		Comment comment = new Comment();
		comment.setContent(content);
		// 下面这行代码没用，天知道我为啥会多写这么一行，可能是为了凑行数吧\(^o^)/~
		comment.setCommenttime(new Date());

		return commentDao.update(comment_id, comment);
	}

	/**
	 * 通过前台传过来的评论的编号获取是谁发表的这个评论的人的详细信息。
	 * 
	 * @param comment_id
	 *            评论在数据库内对应的编号。
	 * @return 评论人的详细信息。
	 * @throws Exception
	 */
	public User getAuthorByComment(Integer comment_id) throws Exception {

		return commentDao.getAuthor(comment_id);
	}

	/**
	 * 根据前台获取到的评论在数据库内的编号，获取评论所在的文章的详细信息。
	 * 
	 * @param comment_id
	 *            评论在数据库内对应的编号。
	 * @return 评论隶属的文章的详细信息。
	 * @throws Exception
	 */
	public Post getPostByComment(Integer comment_id) throws Exception {

		return commentDao.getPost(comment_id);
	}

	/**
	 * 根据前台获取到的标签在数据库内的编号获取拥有这个标签的所有的文章的详细信息。
	 * 
	 * @param tag_id
	 *            标签在数据库内对应的编号。
	 * @return 拥有该标签的所有文章的详细信息。
	 * @throws Exception
	 */
	public ArrayList<Post> getPostsByTag(Integer tag_id) throws Exception {

		return postTagDao.getPosts(tag_id);
	}

	/**
	 * 根据前台或取代的文章在数据库内的编号来获取改文章拥有的所有标签的详细信息。
	 * 
	 * @param post_id
	 *            文章在数据库内对应的编号。
	 * @return 隶属于该文章的所有标签的详细信息。
	 * @throws Exception
	 */
	public ArrayList<Tag> getTagsByPost(Integer post_id) throws Exception {

		return postTagDao.getTags(post_id);
	}

	/**
	 * 根据给定的用户名获取用户的详细信息。
	 * 
	 * @param username
	 *            用户名
	 * @return 用户名对应的详细个人信息。
	 * @throws Exception
	 */
	public User getUserInfo(String username) throws Exception {

		return userDao.getInfo(username);
	}

	/**
	 * 根据给定的博客在数据库内的编号信息获取该篇博客的详细内容。
	 * 
	 * @param post_id
	 *            博客在数据库内对应的编号。
	 * @return 对应于该编号的博客详细信息。
	 * @throws Exception
	 */
	public Post getPostDetails(Integer post_id) throws Exception {

		return postDao.getPost(post_id);
	}

	/**
	 * 根据给定的博客编号获取博客的标题信息。
	 * 
	 * @param post_id
	 *            博客编号
	 * @return 对应于博客编号的博客标题信息。
	 * @throws Exception
	 */
	public String getPostTitle(Integer post_id) throws Exception {

		return this.getPostDetails(post_id).getTitle();
	}

	/**
	 * 根据博客在数据库的编号信息来获取隶属于该文章的评论信息。
	 * 
	 * @param post_id
	 *            博客在数据库中的编号。
	 * @return 隶属于该文章的所有的评论信息。
	 * @throws Exception
	 */
	public ArrayList<Comment> getCommentsByPost(Integer post_id) throws Exception {

		return postDao.getCommentsByPost(post_id);
	}

	/**
	 * 根据用户在数据库中的编号信息获取其用户名，在一些接口种可能会需要。
	 * 
	 * @param user_id
	 *            用户在数据库中的编号。
	 * @return 用户编号对应的用户名。
	 * @throws Exception
	 */
	public String getNameById(Integer user_id) throws Exception {

		return userDao.getNameById(user_id);
	}

	/**
	 * 获取数据库内的所有文章，应用于博客首页文章展示。后续可以添加分页展示效果。
	 * 
	 * @return 数据库内的所有文章。
	 * @throws Exception
	 */
	public ArrayList<Post> getAllPosts() throws Exception {

		return postDao.getAllPosts();
	}

	/**
	 * 根据给定条件获取分页数据。
	 * 
	 * @param currpage
	 *            当前页页码数。
	 * @param next
	 *            是否获取下一页，-1代表上一页，0代表当前页，1代表下一页。
	 * @param pagesize
	 *            页面显示的数据条数。
	 * @return 特定于上述参数条件的结果集。
	 * @throws Exception
	 */
	public ArrayList<Post> getPagination(Integer currpage, Integer next, Integer pagesize) throws Exception {

		return postDao.getPagination(currpage, next, pagesize);
	}

	/**
	 * 根据用户名以及分页参数获取相应的数据结果集。
	 * 
	 * @param username
	 *            用户名
	 * @param currpage
	 *            当前页面页码数
	 * @param next
	 *            是否获取下一页：-1代表上一页；0代表当前页；1代表下一页
	 * @param pagesize
	 *            页面大小
	 * @return 返回用户名下的特定于分页参数的数据结果集。
	 * @throws Exception
	 */
	public ArrayList<Post> getPostsPagination(String username, Integer currpage, Integer next, Integer pagesize)
			throws Exception {

		return userDao.getPostsPagination(username, currpage, next, pagesize);
	}

	/**
	 * 获取数据库内所有的标签信息。
	 * 
	 * @return 返回数据库内所有的标签信息。
	 * @throws Exception
	 */
	public ArrayList<Tag> getAllTags() throws Exception {

		return tagDao.getAllTags();
	}

	/**
	 * 验证给定的用户名是否已经被注册。
	 * 
	 * @param username
	 *            给定的用户名信息。
	 * @return 如果系统内已存在此用户名，则返回False，如果系统内不存在此用户名，这说明该用户名可用。
	 * @throws Exception
	 */
	public boolean validateUsername(String username) throws Exception {

		return userDao.validate(username);
	}

}
