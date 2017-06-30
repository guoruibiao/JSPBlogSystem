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
 * ����ڳ������ʵ�ҵ���������û���¼ע�ᣬ���¡����ۡ���ǩCRUD�ȡ�
 * 
 * @author biao
 *
 */
public class CommonBusiness {

	/**
	 * �û��������DAO��
	 */
	private UserDAO userDao = new UserDAO();

	/**
	 * ������ɾ�Ĳ����DAO��
	 */
	private PostDAO postDao = new PostDAO();

	/**
	 * ������ɾ�Ĳ���ش���DAO��
	 */
	private CommentDAO commentDao = new CommentDAO();

	/**
	 * ��ǩ���DAO��
	 */
	private TagDAO tagDao = new TagDAO();

	/**
	 * ���ڶ�Զ��ϵ���������ǩ������ݻ�ȡ��ش���
	 */
	private PostTagDAO postTagDao = new PostTagDAO();

	/**
	 * ��ǰ̨�����û��������룬ͨ���ײ��̨У���ж��û���ݵĺϷ��ԡ�
	 * 
	 * @param username
	 *            �û���
	 * @param password
	 *            ����
	 * @return �û���ݺϷ��򷵻�TRUE�����򷵻�False��
	 * @throws Exception
	 */
	public boolean login(String username, String password) throws Exception {
		return userDao.login(username, password);
	}

	/**
	 * ��ǰ̨���ձ����ݣ���װ��һ��User���󡣵��õײ�DAO�����û�ע��ҵ����
	 * 
	 * @param username
	 *            �û�������������Ϊ64λ����������˵֧��Unicode�ַ���ʾ��
	 * @param password
	 *            ���룬�ϸ���˵���ܶ೤�����붼�ᱻתΪ32λ�Ĺ�ϣ����
	 * @param email
	 *            ���䣬 ��������Ϊ255λ��
	 * @param sex
	 *            �Ա� Boolean���ͣ�TRUE��ʾ������False��ʾŮ����
	 * @return ����TRUE��ʾע��ɹ��������ʾע��ʧ�ܡ�
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
	 * ����Post��������϶࣬���ﲻ����ֱ�������ڲ����б��ж��ǲ����˰�װģʽ���м򻯲����б�
	 * 
	 * @param post
	 *            �������û�������������Ϣ��Post��ʵ����
	 * @return ����TRUE����post�����������ݿ������Ӧ���ֶ�Ҫ�󡣷���False������Щ�ֶβ�����Ҫ����Ҫ��һ���Ĵ���
	 * @throws Exception
	 */
	public boolean publishPost(Post post) throws Exception {
		return postDao.add(post);
	}

	/**
	 * ͨ��ǰ̨���ݿ��в鵽�����±���������������ݡ�
	 * 
	 * @param post_id
	 *            ���±��
	 * @param post
	 *            �����µ�post�����ݡ�
	 * @return ����TRUE��ʾ�������³ɹ�������False��ʾ���²���ʧ�ܡ�
	 * @throws Exception
	 */
	public boolean updatePost(Integer post_id, Post post) throws Exception {
		return postDao.update(post_id, post);
	}

	/**
	 * ����ǰ̨��ȡ���ĸ����������ݿ��еı�ţ�ɾ����Ӧ���������ݡ�
	 * 
	 * @param post_id
	 *            ���������ݿ��ڵı�š�
	 * @return ����TRUE��ʾɾ���ɹ�������False��ʾɾ��ʧ�ܡ�
	 * @throws Exception
	 */
	public boolean deletePost(Integer post_id) throws Exception {
		return postDao.delete(post_id);
	}

	/**
	 * �����û�������ȡ�����߷�������������¡�
	 * 
	 * @param username
	 *            �������ƣ�Ҳ���Գ�֮Ϊ��������
	 * @return �����б����˵�����û����·�������£�����null�������û���δ��������¡�
	 * @throws Exception
	 */
	public ArrayList<Post> getPosts(String username) throws Exception {

		return userDao.getPosts(username);
	}

	/**
	 * ���ݲ��͵����ݿ��Ż�ȡ�����µ�������Ϣ��
	 * 
	 * @param post_id
	 *            ���ݿ������µı�š�
	 * @return ���ݿ������±�Ŷ�Ӧ����ϸ������Ϣ��
	 * @throws Exception
	 */
	public User getAuthor(Integer post_id) throws Exception {
		return postDao.getAuthor(post_id);
	}

	/**
	 * �����û�����ȡ���û������������������Ϣ��
	 * 
	 * @param username
	 *            �û���
	 * @return �û�����Ӧ�������б�
	 * @throws Exception
	 */
	public ArrayList<Comment> getComments(String username) throws Exception {

		return userDao.getComments(username);
	}

	/**
	 * ����ǰ̨��ȡ�����½��ı�ǩ������������ǩ���󡣲��־û������ݿ��С�
	 * 
	 * @param name
	 *            ��ǩ����
	 * @return ����TRUE��ʾ�½���ǩ�ɹ�������False��ʾ�½���ǩʧ�ܡ�
	 * @throws Exception
	 */
	public boolean addTag(String name) throws Exception {
		Tag tag = new Tag();
		tag.setName(name);
		return tagDao.add(tag);
	}

	/**
	 * ����ǰ̨�������ı�ǩ����ɾ�����ݿ��ڶ�Ӧ�ı�ǩ��
	 * 
	 * @param tagname
	 *            ��ǩ����
	 * @return ����TRUE��ʾɾ����ǩ�ɹ�������False��ʾɾ����ǩʧ�ܡ�
	 * @throws Exception
	 */
	public boolean deleteTag(String tagname) throws Exception {

		return tagDao.delete(tagname);
	}

	/**
	 * ����ǰ̨�������ı�ǩ�����ݿ��ڵı����ɾ�����ݿ��ڶ�Ӧ�ı�ǩ��
	 * 
	 * @param tag_id
	 *            ��ǩ�����ݿ��ڶ�Ӧ��ID��š�
	 * @return ����TRUE��ʾɾ����ǩ�����ɹ�������False��ʾɾ����Ӧ��ǩ����ʧ�ܡ�
	 * @throws Exception
	 */
	public boolean deleteTag(Integer tag_id) throws Exception {

		return tagDao.delete(tag_id);
	}

	/**
	 * ����ǰ̨�������ı�ǩ�����ݿ��ڵı�����޸���Ӧ�ı�ǩ���ơ�
	 * 
	 * @param tag_id
	 *            ��ǩ�����ݿ��ڶ�Ӧ�ı�š�
	 * @param tagname
	 *            ��ǩ�������֡�
	 * @return ����TRUE��ʾ�޸ı�ǩ���Ƴɹ�������False��ʾ�޸���Ӧ��ǩ����ʧ�ܡ�
	 * @throws Exception
	 */
	public boolean updateTag(Integer tag_id, String tagname) throws Exception {
		Tag tag = new Tag();
		tag.setName(tagname);
		return tagDao.update(tag_id, tag);
	}

	/**
	 * ����ǰ̨���ύ��������װһ���Ϸ���Comment�����ύ�����ݿ��С�
	 * 
	 * @param content
	 *            ������ϸ���ݡ�
	 * @param user_id
	 *            ������ID��
	 * @param post_id
	 *            �����۵����µı�š�
	 * @return ����TRUE��ʾ���۳ɹ�������False��ʾ����ʧ�ܡ�
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
	 * ����ǰ̨��ȡ���ı������������ݿ��ڵı����ɾ�������ۡ�
	 * 
	 * @param comment_id
	 *            ����զ���ݿ��ڶ�Ӧ�ı�š�
	 * @return ����TRUE��ʾɾ�����۳ɹ�������False��ʾɾ�����۲���ʧ�ܡ�
	 * @throws Exception
	 */
	public boolean deleteComment(Integer comment_id) throws Exception {

		return commentDao.delete(comment_id);
	}

	/**
	 * ����ǰ̨�����������۵ı��ID���������۵����ݡ�Ĭ����˵���յ�һ�η������۵�ʱ����ʾ��
	 * 
	 * @param comment_id
	 *            ���������ݿ��ڵı��
	 * @param content
	 *            ��������
	 * @return ����TRUE��ʾ�����������ݳɹ�������False��ʾ�������۲���ʧ�ܡ�
	 * @throws Exception
	 */
	public boolean updateComment(Integer comment_id, String content) throws Exception {
		Comment comment = new Comment();
		comment.setContent(content);
		// �������д���û�ã���֪����Ϊɶ���д��ôһ�У�������Ϊ�˴�������\(^o^)/~
		comment.setCommenttime(new Date());

		return commentDao.update(comment_id, comment);
	}

	/**
	 * ͨ��ǰ̨�����������۵ı�Ż�ȡ��˭�����������۵��˵���ϸ��Ϣ��
	 * 
	 * @param comment_id
	 *            ���������ݿ��ڶ�Ӧ�ı�š�
	 * @return �����˵���ϸ��Ϣ��
	 * @throws Exception
	 */
	public User getAuthorByComment(Integer comment_id) throws Exception {

		return commentDao.getAuthor(comment_id);
	}

	/**
	 * ����ǰ̨��ȡ�������������ݿ��ڵı�ţ���ȡ�������ڵ����µ���ϸ��Ϣ��
	 * 
	 * @param comment_id
	 *            ���������ݿ��ڶ�Ӧ�ı�š�
	 * @return �������������µ���ϸ��Ϣ��
	 * @throws Exception
	 */
	public Post getPostByComment(Integer comment_id) throws Exception {

		return commentDao.getPost(comment_id);
	}

	/**
	 * ����ǰ̨��ȡ���ı�ǩ�����ݿ��ڵı�Ż�ȡӵ�������ǩ�����е����µ���ϸ��Ϣ��
	 * 
	 * @param tag_id
	 *            ��ǩ�����ݿ��ڶ�Ӧ�ı�š�
	 * @return ӵ�иñ�ǩ���������µ���ϸ��Ϣ��
	 * @throws Exception
	 */
	public ArrayList<Post> getPostsByTag(Integer tag_id) throws Exception {

		return postTagDao.getPosts(tag_id);
	}

	/**
	 * ����ǰ̨��ȡ�������������ݿ��ڵı������ȡ������ӵ�е����б�ǩ����ϸ��Ϣ��
	 * 
	 * @param post_id
	 *            ���������ݿ��ڶ�Ӧ�ı�š�
	 * @return �����ڸ����µ����б�ǩ����ϸ��Ϣ��
	 * @throws Exception
	 */
	public ArrayList<Tag> getTagsByPost(Integer post_id) throws Exception {

		return postTagDao.getTags(post_id);
	}

	/**
	 * ���ݸ������û�����ȡ�û�����ϸ��Ϣ��
	 * 
	 * @param username
	 *            �û���
	 * @return �û�����Ӧ����ϸ������Ϣ��
	 * @throws Exception
	 */
	public User getUserInfo(String username) throws Exception {

		return userDao.getInfo(username);
	}

	/**
	 * ���ݸ����Ĳ��������ݿ��ڵı����Ϣ��ȡ��ƪ���͵���ϸ���ݡ�
	 * 
	 * @param post_id
	 *            ���������ݿ��ڶ�Ӧ�ı�š�
	 * @return ��Ӧ�ڸñ�ŵĲ�����ϸ��Ϣ��
	 * @throws Exception
	 */
	public Post getPostDetails(Integer post_id) throws Exception {

		return postDao.getPost(post_id);
	}

	/**
	 * ���ݸ����Ĳ��ͱ�Ż�ȡ���͵ı�����Ϣ��
	 * 
	 * @param post_id
	 *            ���ͱ��
	 * @return ��Ӧ�ڲ��ͱ�ŵĲ��ͱ�����Ϣ��
	 * @throws Exception
	 */
	public String getPostTitle(Integer post_id) throws Exception {

		return this.getPostDetails(post_id).getTitle();
	}

	/**
	 * ���ݲ��������ݿ�ı����Ϣ����ȡ�����ڸ����µ�������Ϣ��
	 * 
	 * @param post_id
	 *            ���������ݿ��еı�š�
	 * @return �����ڸ����µ����е�������Ϣ��
	 * @throws Exception
	 */
	public ArrayList<Comment> getCommentsByPost(Integer post_id) throws Exception {

		return postDao.getCommentsByPost(post_id);
	}

	/**
	 * �����û������ݿ��еı����Ϣ��ȡ���û�������һЩ�ӿ��ֿ��ܻ���Ҫ��
	 * 
	 * @param user_id
	 *            �û������ݿ��еı�š�
	 * @return �û���Ŷ�Ӧ���û�����
	 * @throws Exception
	 */
	public String getNameById(Integer user_id) throws Exception {

		return userDao.getNameById(user_id);
	}

	/**
	 * ��ȡ���ݿ��ڵ��������£�Ӧ���ڲ�����ҳ����չʾ������������ӷ�ҳչʾЧ����
	 * 
	 * @return ���ݿ��ڵ��������¡�
	 * @throws Exception
	 */
	public ArrayList<Post> getAllPosts() throws Exception {

		return postDao.getAllPosts();
	}

	/**
	 * ���ݸ���������ȡ��ҳ���ݡ�
	 * 
	 * @param currpage
	 *            ��ǰҳҳ������
	 * @param next
	 *            �Ƿ��ȡ��һҳ��-1������һҳ��0����ǰҳ��1������һҳ��
	 * @param pagesize
	 *            ҳ����ʾ������������
	 * @return �ض����������������Ľ������
	 * @throws Exception
	 */
	public ArrayList<Post> getPagination(Integer currpage, Integer next, Integer pagesize) throws Exception {

		return postDao.getPagination(currpage, next, pagesize);
	}

	/**
	 * �����û����Լ���ҳ������ȡ��Ӧ�����ݽ������
	 * 
	 * @param username
	 *            �û���
	 * @param currpage
	 *            ��ǰҳ��ҳ����
	 * @param next
	 *            �Ƿ��ȡ��һҳ��-1������һҳ��0����ǰҳ��1������һҳ
	 * @param pagesize
	 *            ҳ���С
	 * @return �����û����µ��ض��ڷ�ҳ���������ݽ������
	 * @throws Exception
	 */
	public ArrayList<Post> getPostsPagination(String username, Integer currpage, Integer next, Integer pagesize)
			throws Exception {

		return userDao.getPostsPagination(username, currpage, next, pagesize);
	}

	/**
	 * ��ȡ���ݿ������еı�ǩ��Ϣ��
	 * 
	 * @return �������ݿ������еı�ǩ��Ϣ��
	 * @throws Exception
	 */
	public ArrayList<Tag> getAllTags() throws Exception {

		return tagDao.getAllTags();
	}

	/**
	 * ��֤�������û����Ƿ��Ѿ���ע�ᡣ
	 * 
	 * @param username
	 *            �������û�����Ϣ��
	 * @return ���ϵͳ���Ѵ��ڴ��û������򷵻�False�����ϵͳ�ڲ����ڴ��û�������˵�����û������á�
	 * @throws Exception
	 */
	public boolean validateUsername(String username) throws Exception {

		return userDao.validate(username);
	}

}
