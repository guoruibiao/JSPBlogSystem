package dao;

import dbhelper.DbHelper;

/**
 * DAO�����࣬�������ݿ����õĳ�ʼ�����á�
 * @author biao
 *
 */
public class AbstractDAO {

	/**
	 * ���þ�̬��������ʽ��ϳ����࣬����֤ÿһ��DAO��ʵ���඼�ܼ�ʱ�������ݿ���ص�������
	 */
	static {
		try {
			// �������ݿ��������ݡ�
			DbHelper.register();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	// TODO ����ÿһ���������ʵ������ԣ�ÿһ������������ʮ�ְ�ȫ�ģ��������finally�����ٴ��ͷ�conn���󣬽��ж�����֤��
	
}
