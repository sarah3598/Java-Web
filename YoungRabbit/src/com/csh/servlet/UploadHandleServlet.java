package com.csh.servlet;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadBase;
import org.apache.commons.fileupload.ProgressListener;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.csh.dao.BaseDao;
import com.csh.service.CompanyProductService;
import com.csh.service.JsonService;
import com.csh.service.ProductService;
import com.csh.service.SlideService;

/**
 * Servlet implementation class UploadServlet
 */

public class UploadHandleServlet extends HttpServlet {

	private static final int MEMORY_THRESHOLD = 1024 * 1024 * 3; // 3MB
	private static final int MAX_FILE_SIZE = 1024 * 1024 * 10; // 40MB
	private static final int MAX_REQUEST_SIZE = 1024 * 1024 * 100; // 50MB

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}
	
	private void addSlideImage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 文件上传相对路径
				String upload_path = "/files/upload/slide_image";
				// 得到上传文件的保存目录，将上传的文件存放于WEB-INF目录下，不允许外界直接访问，保证上传文件的安全
				String savePath = this.getServletContext().getRealPath(upload_path);
				// 上传时生成的临时文件保存目录
				String tempPath = this.getServletContext().getRealPath("/files/upload/temp");
				File tmpFile = new File(tempPath);
				if (!tmpFile.exists()) {
					// 创建临时目录
					tmpFile.mkdir();
				}

				// 消息提示
				String msg = "";
				String json_data = "";
				String normal_json = "";

				int slide_id = -1;

				Boolean all_image_upload_flag = true;
				try {
					// 使用Apache文件上传组件处理文件上传步骤：
					// 1、创建一个DiskFileItemFactory工厂
					DiskFileItemFactory factory = new DiskFileItemFactory();
					// 设置工厂的缓冲区的大小，当上传的文件大小超过缓冲区的大小时，就会生成一个临时文件存放到指定的临时目录当中。
					factory.setSizeThreshold(1024 * 100);// 设置缓冲区的大小为100KB，如果不指定，那么缓冲区的大小默认是10KB
					// 设置上传时生成的临时文件的保存目录
					factory.setRepository(tmpFile);
					// 2、创建一个文件上传解析器
					ServletFileUpload upload = new ServletFileUpload(factory);
					// 监听文件上传进度
					upload.setProgressListener(new ProgressListener() {
						public void update(long pBytesRead, long pContentLength, int arg2) {
							System.out.println("文件大小为：" + pContentLength + ",当前已处理：" + pBytesRead);
							/**
							 * 文件大小为：14608,当前已处理：4096 文件大小为：14608,当前已处理：7367
							 * 文件大小为：14608,当前已处理：11419 文件大小为：14608,当前已处理：14608
							 */
						}
					});
					// 解决上传文件名的中文乱码
					upload.setHeaderEncoding("UTF-8");
					// 3、判断提交上来的数据是否是上传表单的数据
					if (!ServletFileUpload.isMultipartContent(request)) {
						// 按照传统方式获取数据
						msg = "图片类型错误！";
						json_data = JsonService.convertKeyValToJsonObject("SLIDE_ID", null);
						normal_json = JsonService.getOperateResultNormalJson("true", json_data, msg);
						JsonService.ResponseJson(request, response, normal_json);
						return;
					}

					// 设置上传单个文件的大小的最大值，目前是设置为1024*1024字节，也就是1MB
					upload.setFileSizeMax(MAX_FILE_SIZE);
					// 设置上传文件总量的最大值，最大值=同时上传的多个文件的大小的最大值的和，目前设置为10MB
					upload.setSizeMax(MAX_REQUEST_SIZE);

					// 4、使用ServletFileUpload解析器解析上传数据，解析结果返回的是一个List<FileItem>集合，每一个FileItem对应一个Form表单的输入项
					@SuppressWarnings("unchecked")
					List<FileItem> list = upload.parseRequest(request);
					for (FileItem item : list) {
						// 如果fileitem中封装的是普通输入项的数据
						if (item.isFormField()) {
							String name = item.getFieldName();
							// 解决普通输入项的数据的中文乱码问题
							String value = item.getString("UTF-8");
							// value = new String(value.getBytes("iso8859-1"),"UTF-8");
							System.out.println(name + "=" + value);
						} else {// 如果fileitem中封装的是上传文件
								// 得到上传的文件名称，
							String filename = item.getName();
							System.out.println(filename);
							if (filename == null || filename.trim().equals("")) {
								continue;
							}
							// 注意：不同的浏览器提交的文件名是不一样的，有些浏览器提交上来的文件名是带有路径的，如：
							// c:\a\b\1.txt，而有些只是单纯的文件名，如：1.txt
							// 处理获取到的上传文件的文件名的路径部分，只保留文件名部分
							filename = filename.substring(filename.lastIndexOf("\\") + 1);
							// 得到上传文件的扩展名
							String fileExtName = filename.substring(filename.lastIndexOf(".") + 1);
							// 如果需要限制上传的文件类型，那么可以通过文件的扩展名来判断上传的文件类型是否合法
							System.out.println("上传的图片的扩展名是：" + fileExtName);
							// 获取item中的上传文件的输入流
							InputStream in = item.getInputStream();

							// 得到唯一的文件名
							String uniqueFilename = getUniqueFileName(filename);
							// 获取下级相对路径
							String relative_hash_path = getRelativeHashPath(uniqueFilename);
							// 得到文件的保存目录
							String realSavePath = makeRealPath(relative_hash_path, savePath);
							// 创建一个文件输出流
							FileOutputStream out = new FileOutputStream(realSavePath + "\\" + uniqueFilename);
							// 创建一个缓冲区
							byte buffer[] = new byte[1024];
							// 判断输入流中的数据是否已经读完的标识
							int len = 0;
							// 循环将输入流读入到缓冲区当中，(len=in.read(buffer))>0就表示in里面还有数据
							while ((len = in.read(buffer)) > 0) {
								// 使用FileOutputStream输出流将缓冲区的数据写入到指定的目录(savePath + "\\"
								// + filename)当中
								out.write(buffer, 0, len);
							}
							// 关闭输入流
							in.close();
							// 关闭输出流
							out.close();
							// 删除处理文件上传时生成的临时文件
							// item.delete();
						

							// 保存图片路径
							String image_path = upload_path + relative_hash_path + "/" + uniqueFilename;
							String insertSql = "INSERT INTO SLIDER (URL) VALUES (?)";
							Object[] param = new Object[1];
							param[0] = image_path;
							int flag = BaseDao.exeSql(insertSql, param);
							if (flag == -1) {
								all_image_upload_flag = false;
								break;
							}
							String get_slide_id_Sql = "SELECT SLIDE_ID FROM SLIDER WHERE URL=?";			
							slide_id = SlideService.getSlideID(get_slide_id_Sql, param);
						}
					}
				} catch (Exception e) {
					all_image_upload_flag = false;
					e.printStackTrace();
				}

				// 所有图像都上传成功，返回成功；否则，返回失败！
				if (all_image_upload_flag && (slide_id > 0)) {				
					msg = "图片上传成功！";			
					json_data = JsonService.convertKeyValToJsonObject("SLIDE_ID", Integer.toString(slide_id));
					normal_json = JsonService.getOperateResultNormalJson("true", json_data, msg);

				} else {
					msg = "图片上传失败！";
					json_data = JsonService.convertKeyValToJsonObject("SLIDE_ID", null);
					normal_json = JsonService.getOperateResultNormalJson("true", json_data, msg);
				}
				JsonService.ResponseJson(request, response, normal_json);
		
	}

	private void updateSlideImage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 消息提示
		String msg = "";
		String json_data = "";
		String normal_json = "";
		
		// 更新之前，删除原有的图片路径
		String SLIDE_ID = request.getParameter("SLIDE_ID");
		
		
		// 文件上传相对路径
		String upload_path = "/files/upload/slide_image";
		// 得到上传文件的保存目录，将上传的文件存放于WEB-INF目录下，不允许外界直接访问，保证上传文件的安全
		String savePath = this.getServletContext().getRealPath(upload_path);
		// 上传时生成的临时文件保存目录
		String tempPath = this.getServletContext().getRealPath("/files/upload/temp");
		File tmpFile = new File(tempPath);
		if (!tmpFile.exists()) {
			// 创建临时目录
			tmpFile.mkdir();
		}

		Boolean all_image_upload_flag = true;
		try {
			// 使用Apache文件上传组件处理文件上传步骤：
			// 1、创建一个DiskFileItemFactory工厂
			DiskFileItemFactory factory = new DiskFileItemFactory();
			// 设置工厂的缓冲区的大小，当上传的文件大小超过缓冲区的大小时，就会生成一个临时文件存放到指定的临时目录当中。
			factory.setSizeThreshold(1024 * 100);// 设置缓冲区的大小为100KB，如果不指定，那么缓冲区的大小默认是10KB
			// 设置上传时生成的临时文件的保存目录
			factory.setRepository(tmpFile);
			// 2、创建一个文件上传解析器
			ServletFileUpload upload = new ServletFileUpload(factory);
			// 监听文件上传进度
			upload.setProgressListener(new ProgressListener() {
				public void update(long pBytesRead, long pContentLength, int arg2) {
					System.out.println("图片大小为：" + pContentLength + ",当前已处理：" + pBytesRead);
					/**
					 * 文件大小为：14608,当前已处理：4096 文件大小为：14608,当前已处理：7367
					 * 文件大小为：14608,当前已处理：11419 文件大小为：14608,当前已处理：14608
					 */
				}
			});
			// 解决上传文件名的中文乱码
			upload.setHeaderEncoding("UTF-8");
			// 3、判断提交上来的数据是否是上传表单的数据
			if (!ServletFileUpload.isMultipartContent(request)) {
				// 按照传统方式获取数据
				msg = "图片类型错误！";
				json_data = JsonService.convertKeyValToJsonObject("SLIDE_ID", null);
				normal_json = JsonService.getOperateResultNormalJson("true", json_data, msg);
				JsonService.ResponseJson(request, response, normal_json);
				return;
			}

			// 设置上传单个文件的大小的最大值，目前是设置为1024*1024字节，也就是1MB
			upload.setFileSizeMax(MAX_FILE_SIZE);
			// 设置上传文件总量的最大值，最大值=同时上传的多个文件的大小的最大值的和，目前设置为10MB
			upload.setSizeMax(MAX_REQUEST_SIZE);

			// 4、使用ServletFileUpload解析器解析上传数据，解析结果返回的是一个List<FileItem>集合，每一个FileItem对应一个Form表单的输入项
			@SuppressWarnings("unchecked")
			List<FileItem> list = upload.parseRequest(request);
			for (FileItem item : list) {
				// 如果fileitem中封装的是普通输入项的数据
				if (item.isFormField()) {
					String name = item.getFieldName();
					// 解决普通输入项的数据的中文乱码问题
					String value = item.getString("UTF-8");
					// value = new String(value.getBytes("iso8859-1"),"UTF-8");
					System.out.println(name + "=" + value);
				} else {// 如果fileitem中封装的是上传文件
						// 得到上传的文件名称，
					String filename = item.getName();
					System.out.println(filename);
					if (filename == null || filename.trim().equals("")) {
						continue;
					}
					// 注意：不同的浏览器提交的文件名是不一样的，有些浏览器提交上来的文件名是带有路径的，如：
					// c:\a\b\1.txt，而有些只是单纯的文件名，如：1.txt
					// 处理获取到的上传文件的文件名的路径部分，只保留文件名部分
					filename = filename.substring(filename.lastIndexOf("\\") + 1);
					// 得到上传文件的扩展名
					String fileExtName = filename.substring(filename.lastIndexOf(".") + 1);
					// 如果需要限制上传的文件类型，那么可以通过文件的扩展名来判断上传的文件类型是否合法
					System.out.println("上传的图片的扩展名是：" + fileExtName);
					// 获取item中的上传文件的输入流
					InputStream in = item.getInputStream();

					// 得到唯一的文件名
					String uniqueFilename = getUniqueFileName(filename);
					// 获取下级相对路径
					String relative_hash_path = getRelativeHashPath(uniqueFilename);
					// 得到文件的保存目录
					String realSavePath = makeRealPath(relative_hash_path, savePath);
					// 创建一个文件输出流
					FileOutputStream out = new FileOutputStream(realSavePath + "\\" + uniqueFilename);
					// 创建一个缓冲区
					byte buffer[] = new byte[1024];
					// 判断输入流中的数据是否已经读完的标识
					int len = 0;
					// 循环将输入流读入到缓冲区当中，(len=in.read(buffer))>0就表示in里面还有数据
					while ((len = in.read(buffer)) > 0) {
						// 使用FileOutputStream输出流将缓冲区的数据写入到指定的目录(savePath + "\\"
						// + filename)当中
						out.write(buffer, 0, len);
					}
					// 关闭输入流
					in.close();
					// 关闭输出流
					out.close();
					// 删除处理文件上传时生成的临时文件
					// item.delete();

					// 保存图片路径
					String image_path = upload_path + relative_hash_path + "/" + uniqueFilename;
					String insertSql = "UPDATE SLIDER SET URL=? WHERE SLIDE_ID=?";
					Object[] param = new Object[2];
					param[0] = image_path;
					param[1] = SLIDE_ID;
					int flag = BaseDao.exeSql(insertSql, param);
					if (flag == -1) {
						all_image_upload_flag = false;
						break;
					}
				}
			}
		} catch (Exception e) {
			all_image_upload_flag = false;
			e.printStackTrace();
		}

		// 所有图像都上传成功，返回成功；否则，返回失败！
		if (all_image_upload_flag) {
			msg = "图片上传成功！";
			json_data = JsonService.convertKeyValToJsonObject("SLIDE_ID", SLIDE_ID);
			normal_json = JsonService.getOperateResultNormalJson("true", json_data, msg);

		} else {
			msg = "图片上传失败！";
			json_data = JsonService.convertKeyValToJsonObject("SLIDE_ID", null);
			normal_json = JsonService.getOperateResultNormalJson("true", json_data, msg);
		}
		JsonService.ResponseJson(request, response, normal_json);
	}		

	public void addProductImage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 文件上传相对路径
		String upload_path = "/files/upload/product_image";
		// 得到上传文件的保存目录，将上传的文件存放于WEB-INF目录下，不允许外界直接访问，保证上传文件的安全
		String savePath = this.getServletContext().getRealPath(upload_path);
		// 上传时生成的临时文件保存目录
		String tempPath = this.getServletContext().getRealPath("/files/upload/temp");
		File tmpFile = new File(tempPath);
		if (!tmpFile.exists()) {
			// 创建临时目录
			tmpFile.mkdir();
		}

		// 消息提示
		String msg = "";
		String json_data = "";
		String normal_json = "";

		int good_id = -1;
		int image_id = -1;

		Boolean all_image_upload_flag = true;
		try {
			// 使用Apache文件上传组件处理文件上传步骤：
			// 1、创建一个DiskFileItemFactory工厂
			DiskFileItemFactory factory = new DiskFileItemFactory();
			// 设置工厂的缓冲区的大小，当上传的文件大小超过缓冲区的大小时，就会生成一个临时文件存放到指定的临时目录当中。
			factory.setSizeThreshold(1024 * 100);// 设置缓冲区的大小为100KB，如果不指定，那么缓冲区的大小默认是10KB
			// 设置上传时生成的临时文件的保存目录
			factory.setRepository(tmpFile);
			// 2、创建一个文件上传解析器
			ServletFileUpload upload = new ServletFileUpload(factory);
			// 监听文件上传进度
			upload.setProgressListener(new ProgressListener() {
				public void update(long pBytesRead, long pContentLength, int arg2) {
					System.out.println("文件大小为：" + pContentLength + ",当前已处理：" + pBytesRead);
					/**
					 * 文件大小为：14608,当前已处理：4096 文件大小为：14608,当前已处理：7367
					 * 文件大小为：14608,当前已处理：11419 文件大小为：14608,当前已处理：14608
					 */
				}
			});
			// 解决上传文件名的中文乱码
			upload.setHeaderEncoding("UTF-8");
			// 3、判断提交上来的数据是否是上传表单的数据
			if (!ServletFileUpload.isMultipartContent(request)) {
				// 按照传统方式获取数据
				msg = "图片类型错误！";
				json_data = JsonService.convertKeyValToJsonObject("IMAGE_ID", null);
				normal_json = JsonService.getOperateResultNormalJson("true", json_data, msg);
				JsonService.ResponseJson(request, response, normal_json);
				return;
			}

			// 设置上传单个文件的大小的最大值，目前是设置为1024*1024字节，也就是1MB
			upload.setFileSizeMax(MAX_FILE_SIZE);
			// 设置上传文件总量的最大值，最大值=同时上传的多个文件的大小的最大值的和，目前设置为10MB
			upload.setSizeMax(MAX_REQUEST_SIZE);

			// 4、使用ServletFileUpload解析器解析上传数据，解析结果返回的是一个List<FileItem>集合，每一个FileItem对应一个Form表单的输入项
			@SuppressWarnings("unchecked")
			List<FileItem> list = upload.parseRequest(request);
			for (FileItem item : list) {
				// 如果fileitem中封装的是普通输入项的数据
				if (item.isFormField()) {
					String name = item.getFieldName();
					// 解决普通输入项的数据的中文乱码问题
					String value = item.getString("UTF-8");
					// value = new String(value.getBytes("iso8859-1"),"UTF-8");
					System.out.println(name + "=" + value);
				} else {// 如果fileitem中封装的是上传文件
						// 得到上传的文件名称，
					String filename = item.getName();
					System.out.println(filename);
					if (filename == null || filename.trim().equals("")) {
						continue;
					}
					// 注意：不同的浏览器提交的文件名是不一样的，有些浏览器提交上来的文件名是带有路径的，如：
					// c:\a\b\1.txt，而有些只是单纯的文件名，如：1.txt
					// 处理获取到的上传文件的文件名的路径部分，只保留文件名部分
					filename = filename.substring(filename.lastIndexOf("\\") + 1);
					// 得到上传文件的扩展名
					String fileExtName = filename.substring(filename.lastIndexOf(".") + 1);
					// 如果需要限制上传的文件类型，那么可以通过文件的扩展名来判断上传的文件类型是否合法
					System.out.println("上传的图片的扩展名是：" + fileExtName);
					// 获取item中的上传文件的输入流
					InputStream in = item.getInputStream();

					// 得到唯一的文件名
					String uniqueFilename = getUniqueFileName(filename);
					// 获取下级相对路径
					String relative_hash_path = getRelativeHashPath(uniqueFilename);
					// 得到文件的保存目录
					String realSavePath = makeRealPath(relative_hash_path, savePath);
					// 创建一个文件输出流
					FileOutputStream out = new FileOutputStream(realSavePath + "\\" + uniqueFilename);
					// 创建一个缓冲区
					byte buffer[] = new byte[1024];
					// 判断输入流中的数据是否已经读完的标识
					int len = 0;
					// 循环将输入流读入到缓冲区当中，(len=in.read(buffer))>0就表示in里面还有数据
					while ((len = in.read(buffer)) > 0) {
						// 使用FileOutputStream输出流将缓冲区的数据写入到指定的目录(savePath + "\\"
						// + filename)当中
						out.write(buffer, 0, len);
					}
					// 关闭输入流
					in.close();
					// 关闭输出流
					out.close();
					// 删除处理文件上传时生成的临时文件
					// item.delete();

					// TODO:此处需要考虑一个产品上传多张图片的情况
					if (good_id == -1) {// 初始时获取good id,并获取到image id
						good_id = CompanyProductService.getNextGoodID();
						String insert_sql0 = "INSERT INTO WEB_IMAGE(GOOD_ID) VALUES(?)";
						Object[] param0 = new Object[1];
						param0[0] = good_id;
						int flag0 = BaseDao.exeSql(insert_sql0, param0);
						int flag1 = -1;
						if (flag0 == 1) {
							// get image id
							String get_image_id_Sql = "SELECT IMAGE_ID FROM WEB_IMAGE WHERE GOOD_ID=?";
							image_id = CompanyProductService.getProducImageID(get_image_id_Sql, param0);

							// insert image id into WEB_GOODS_BASIC_INFO
							String update_sql0 = "UPDATE　WEB_GOODS_BASIC_INFO SET IMAGE_ID=? WHERE GOOD_ID=?";
							Object[] param2 = new Object[2];
							param2[0] = image_id;
							param2[1] = good_id;
							flag1 = BaseDao.exeSql(update_sql0, param2);
						}

						if ((flag0 != 1) && (flag1 != 1)) {
							msg = "图片上传失败！";
							json_data = JsonService.convertKeyValToJsonObject("GOOD_ID", Integer.toString(good_id));
							normal_json = JsonService.getOperateResultNormalJson("true", json_data, msg);
							JsonService.ResponseJson(request, response, normal_json);
							break;
						}
					}

					// 保存图片路径
					String image_path = upload_path + relative_hash_path + "/" + uniqueFilename;
					String insertSql = "INSERT INTO WEB_IMAGE_URL (IMAGE_ID,IMAGE_PATH) VALUES (?,?)";
					Object[] param = new Object[2];
					param[0] = image_id;
					param[1] = image_path;
					int flag = BaseDao.exeSql(insertSql, param);
					if (flag == -1) {
						all_image_upload_flag = false;
						break;
					}
				}
			}
		} catch (Exception e) {
			all_image_upload_flag = false;
			e.printStackTrace();
		}

		// 所有图像都上传成功，返回成功；否则，返回失败！
		if (all_image_upload_flag && (good_id > 0) && (image_id > 0)) {
			msg = "图片上传成功！";
			json_data = JsonService.convertKeyValToJsonObject("GOOD_ID", Integer.toString(good_id));
			normal_json = JsonService.getOperateResultNormalJson("true", json_data, msg);

		} else {
			msg = "图片上传失败！";
			json_data = JsonService.convertKeyValToJsonObject("GOOD_ID", null);
			normal_json = JsonService.getOperateResultNormalJson("true", json_data, msg);
		}
		JsonService.ResponseJson(request, response, normal_json);
	}

	public void updateProductImage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 消息提示
		String msg = "";
		String json_data = "";
		String normal_json = "";
		
		// 更新之前，删除原有的图片路径
		String IMAGE_ID = request.getParameter("IMAGE_ID");
		if (IMAGE_ID != null) {
			String delSql = "DELETE *　FROM WEB_IMAGE_URL WHERE IMAGE_ID=?";
			Object[] param = new Object[1];
			param[0] = IMAGE_ID;
			BaseDao.exeSql(delSql, param);

		} else {
			msg = "图片更新失败！";
			json_data = JsonService.convertKeyValToJsonObject("IMAGE_ID", null);
			normal_json = JsonService.getOperateResultNormalJson("true", json_data, msg);
			JsonService.ResponseJson(request, response, normal_json);
			return;
		}
		
		// 文件上传相对路径
		String upload_path = "/files/upload/product_image";
		// 得到上传文件的保存目录，将上传的文件存放于WEB-INF目录下，不允许外界直接访问，保证上传文件的安全
		String savePath = this.getServletContext().getRealPath(upload_path);
		// 上传时生成的临时文件保存目录
		String tempPath = this.getServletContext().getRealPath("/files/upload/temp");
		File tmpFile = new File(tempPath);
		if (!tmpFile.exists()) {
			// 创建临时目录
			tmpFile.mkdir();
		}

		Boolean all_image_upload_flag = true;
		try {
			// 使用Apache文件上传组件处理文件上传步骤：
			// 1、创建一个DiskFileItemFactory工厂
			DiskFileItemFactory factory = new DiskFileItemFactory();
			// 设置工厂的缓冲区的大小，当上传的文件大小超过缓冲区的大小时，就会生成一个临时文件存放到指定的临时目录当中。
			factory.setSizeThreshold(1024 * 100);// 设置缓冲区的大小为100KB，如果不指定，那么缓冲区的大小默认是10KB
			// 设置上传时生成的临时文件的保存目录
			factory.setRepository(tmpFile);
			// 2、创建一个文件上传解析器
			ServletFileUpload upload = new ServletFileUpload(factory);
			// 监听文件上传进度
			upload.setProgressListener(new ProgressListener() {
				public void update(long pBytesRead, long pContentLength, int arg2) {
					System.out.println("图片大小为：" + pContentLength + ",当前已处理：" + pBytesRead);
					/**
					 * 文件大小为：14608,当前已处理：4096 文件大小为：14608,当前已处理：7367
					 * 文件大小为：14608,当前已处理：11419 文件大小为：14608,当前已处理：14608
					 */
				}
			});
			// 解决上传文件名的中文乱码
			upload.setHeaderEncoding("UTF-8");
			// 3、判断提交上来的数据是否是上传表单的数据
			if (!ServletFileUpload.isMultipartContent(request)) {
				// 按照传统方式获取数据
				msg = "图片类型错误！";
				json_data = JsonService.convertKeyValToJsonObject("IMAGE_ID", null);
				normal_json = JsonService.getOperateResultNormalJson("true", json_data, msg);
				JsonService.ResponseJson(request, response, normal_json);
				return;
			}

			// 设置上传单个文件的大小的最大值，目前是设置为1024*1024字节，也就是1MB
			upload.setFileSizeMax(MAX_FILE_SIZE);
			// 设置上传文件总量的最大值，最大值=同时上传的多个文件的大小的最大值的和，目前设置为10MB
			upload.setSizeMax(MAX_REQUEST_SIZE);

			// 4、使用ServletFileUpload解析器解析上传数据，解析结果返回的是一个List<FileItem>集合，每一个FileItem对应一个Form表单的输入项
			@SuppressWarnings("unchecked")
			List<FileItem> list = upload.parseRequest(request);
			for (FileItem item : list) {
				// 如果fileitem中封装的是普通输入项的数据
				if (item.isFormField()) {
					String name = item.getFieldName();
					// 解决普通输入项的数据的中文乱码问题
					String value = item.getString("UTF-8");
					// value = new String(value.getBytes("iso8859-1"),"UTF-8");
					System.out.println(name + "=" + value);
				} else {// 如果fileitem中封装的是上传文件
						// 得到上传的文件名称，
					String filename = item.getName();
					System.out.println(filename);
					if (filename == null || filename.trim().equals("")) {
						continue;
					}
					// 注意：不同的浏览器提交的文件名是不一样的，有些浏览器提交上来的文件名是带有路径的，如：
					// c:\a\b\1.txt，而有些只是单纯的文件名，如：1.txt
					// 处理获取到的上传文件的文件名的路径部分，只保留文件名部分
					filename = filename.substring(filename.lastIndexOf("\\") + 1);
					// 得到上传文件的扩展名
					String fileExtName = filename.substring(filename.lastIndexOf(".") + 1);
					// 如果需要限制上传的文件类型，那么可以通过文件的扩展名来判断上传的文件类型是否合法
					System.out.println("上传的图片的扩展名是：" + fileExtName);
					// 获取item中的上传文件的输入流
					InputStream in = item.getInputStream();

					// 得到唯一的文件名
					String uniqueFilename = getUniqueFileName(filename);
					// 获取下级相对路径
					String relative_hash_path = getRelativeHashPath(uniqueFilename);
					// 得到文件的保存目录
					String realSavePath = makeRealPath(relative_hash_path, savePath);
					// 创建一个文件输出流
					FileOutputStream out = new FileOutputStream(realSavePath + "\\" + uniqueFilename);
					// 创建一个缓冲区
					byte buffer[] = new byte[1024];
					// 判断输入流中的数据是否已经读完的标识
					int len = 0;
					// 循环将输入流读入到缓冲区当中，(len=in.read(buffer))>0就表示in里面还有数据
					while ((len = in.read(buffer)) > 0) {
						// 使用FileOutputStream输出流将缓冲区的数据写入到指定的目录(savePath + "\\"
						// + filename)当中
						out.write(buffer, 0, len);
					}
					// 关闭输入流
					in.close();
					// 关闭输出流
					out.close();
					// 删除处理文件上传时生成的临时文件
					// item.delete();

					// 保存图片路径
					String image_path = upload_path + relative_hash_path + "/" + uniqueFilename;
					String insertSql = "INSERT INTO WEB_IMAGE_URL (IMAGE_ID,IMAGE_PATH) VALUES (?,?)";
					Object[] param = new Object[2];
					param[0] = IMAGE_ID;
					param[1] = image_path;
					int flag = BaseDao.exeSql(insertSql, param);
					if (flag == -1) {
						all_image_upload_flag = false;
						break;
					}
				}
			}
		} catch (Exception e) {
			all_image_upload_flag = false;
			e.printStackTrace();
		}

		// 所有图像都上传成功，返回成功；否则，返回失败！
		if (all_image_upload_flag) {
			msg = "图片上传成功！";
			json_data = JsonService.convertKeyValToJsonObject("IMAGE_ID", IMAGE_ID);
			normal_json = JsonService.getOperateResultNormalJson("true", json_data, msg);

		} else {
			msg = "图片上传失败！";
			json_data = JsonService.convertKeyValToJsonObject("IMAGE_ID", null);
			normal_json = JsonService.getOperateResultNormalJson("true", json_data, msg);
		}
		JsonService.ResponseJson(request, response, normal_json);
	}

	/**
	 * @param filename
	 *            :文件名
	 * @return :返回唯一的文件名
	 */
	private String getUniqueFileName(String filename) { // 2.jpg
		// 为防止文件覆盖的现象发生，要为上传文件产生一个唯一的文件名
		return UUID.randomUUID().toString() + "_" + filename;
	}

	private String makeRealPath(String relative_hash_path, String savePath) {

		String dir = savePath + "\\" + relative_hash_path; // upload\2\3
															// upload\3\5
		// File既可以代表文件也可以代表目录
		File file = new File(dir);
		// 如果目录不存在
		if (!file.exists()) {
			// 创建目录
			file.mkdirs();
		}
		return dir;
	}

	/**
	 * hash目录打散存储
	 * 
	 * @param filename
	 *            :文件名
	 * @return
	 */
	private String getRelativeHashPath(String filename) {
		// 得到文件名的hashCode的值，得到的就是filename这个字符串对象在内存中的地址
		int hashcode = filename.hashCode();
		int dir1 = hashcode & 0xf; // 0--15
		int dir2 = (hashcode & 0xf0) >> 4; // 0-15
		// 构造新的相对路径
		String relative_dir = "/" + dir1 + "/" + dir2; // upload\2\3
		return relative_dir;
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String method = request.getParameter("method");
		switch (method) {
		case "upload_product_image":
			addProductImage(request, response);
			break;
		case "update_slide_image":
			updateSlideImage(request, response);
			break;	
		case "upload_slide_image":
			addSlideImage(request, response);
			break;	
		case "update_product_image":
			updateProductImage(request, response);
			break;
		}
	}

	


}