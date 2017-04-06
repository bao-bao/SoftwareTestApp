package Util;

/* Created by AMXPC on 2017/4/5. */

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.util.List;
import java.util.Objects;

public class FileUploader {
    private String filePath;
    private String fileName;
    private File uploadFile;

    public FileUploader(HttpServletRequest request) {
        // Create a factory for disk-based file items
        DiskFileItemFactory factory = new DiskFileItemFactory();

        // 当上传文件太大时，因为虚拟机能使用的内存是有限的，所以此时要通过临时文件来实现上传文件的保存
        // 此方法是设置是否使用临时文件的临界值（单位：字节）
        factory.setSizeThreshold(1024 * 1024);

        // 与上一个结合使用，设置临时文件的路径（绝对路径）
        File repository = new File(request.getSession().getServletContext().getRealPath("temp"));
        factory.setRepository(repository);

        // Create a new file upload handler
        ServletFileUpload upload = new ServletFileUpload(factory);

        // 设置上传内容的大小限制（单位：字节）
        upload.setSizeMax(1024 * 1024);

        // Parse the request
        List<FileItem> items = null;
        try {
            items = upload.parseRequest(request);
        } catch (FileUploadException e) {
            e.printStackTrace();
        }

        assert items != null;
        for(FileItem fileItem : items) {
            if (!fileItem.isFormField()) {
                //如果是文件字段
                String clientName = fileItem.getName();
                if (clientName.contains("\\")) {                //如果包含"\"表示是一个带路径的名字,则截取最后的文件名
                    fileName = clientName.substring(clientName.lastIndexOf("\\")).substring(1);
                } else {
                    fileName = clientName;
                }
                filePath = request.getSession().getServletContext().getRealPath("upload");

                //读取xls文件，其他文件略过
                if (fileName.endsWith(".xls")) {
                    InputStream inputStream2 = null;
                    BufferedOutputStream bos = null;
                    try {
                        //从Request输入流中读取文件，并写入到服务器
                        inputStream2 = fileItem.getInputStream();
                        //在服务器端创建文件
                        uploadFile = new File(filePath + "/" + fileName);
                        if(uploadFile.exists()) {
                            uploadFile.delete();
                            uploadFile = new File(filePath + "/" + fileName);
                        }
                        if(!uploadFile.getParentFile().exists()) {
                            //如果目标文件所在的目录不存在，则创建父目录
                            System.out.println("文件所在目录不存在");
                        }
                        if(uploadFile.createNewFile()) {
                            System.out.println("创建" + clientName + "成功");
                        }
                        bos = new BufferedOutputStream(new FileOutputStream(uploadFile));

                        byte[] buffer = new byte[10 * 1024];
                        int len;
                        while ((len = inputStream2.read(buffer, 0, 10 * 1024)) != -1) {
                            bos.write(buffer, 0, len);
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    } finally {
                        //关闭资源
                        try {
                            if (bos != null) {
                                bos.close();
                            }
                            if (inputStream2 != null) {
                                inputStream2.close();
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    System.out.println("您上传文件" + clientName + "成功");
                } else {
                    fileName = "= wrong =";
                }
            } else {
                if(Objects.equals(fileItem.getFieldName(), "project")) {
                    request.setAttribute("project", fileItem.getString());
                }
                if(Objects.equals(fileItem.getFieldName(), "tester")) {
                    request.setAttribute("tester", fileItem.getString());
                }
            }
        }
    }

    public String getFilePath() {
        return filePath;
    }

    public String getFileName() {
        return fileName;
    }

    public File getUploadFile() {
        return uploadFile;
    }
}
