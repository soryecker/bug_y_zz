import java.io.*;
import java.net.*;

public class Tool
{
	public static String doGet(String httpurl) {
        HttpURLConnection connection = null;
        InputStream is = null;
        BufferedReader br = null;
        String result = null;// 返回结果字符串
        try {
            // 创建远程url连接对象
            URL url = new URL(httpurl);
            // 通过远程url连接对象打开一个连接，强转成httpURLConnection类
            connection = (HttpURLConnection) url.openConnection();
            // 设置连接方式：get
            connection.setRequestMethod("GET");
            connection.setRequestProperty("User-Agent","Mozilla/5.0 (Windows NT 6.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/64.0.3282.119 Safari/537.36");//电脑级ua
            // 设置连接主机服务器的超时时间：15000毫秒
            connection.setConnectTimeout(15000);
            // 设置读取远程返回的数据时间：60000毫秒
            connection.setReadTimeout(60000);
            // 发送请求
            connection.connect();
            // 通过connection连接，获取输入流
            if (connection.getResponseCode() == 200) {
                is = connection.getInputStream();
                // 封装输入流is，并指定字符集
                br = new BufferedReader(new InputStreamReader(is, "GB2312"));
                // 存放数据
                StringBuffer sbf = new StringBuffer();
                String temp = null;
                while ((temp = br.readLine()) != null) {
                    sbf.append(temp);
                    sbf.append("\r\n");
                }
                result = sbf.toString();
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // 关闭资源
            if (null != br) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if (null != is) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            connection.disconnect();// 关闭远程连接
        }

        return result;
    }



	public static String doPost(String httpUrl, String param)
	{


		HttpURLConnection connection = null;
		InputStream is = null;
		OutputStream os = null;
		BufferedReader br = null;
		String result = null;
		try
		{
			URL url = new URL(httpUrl);
			// 通过远程url连接对象打开连接
			connection = (HttpURLConnection) url.openConnection();
			// 设置连接请求方式
			connection.setRequestMethod("POST");
			// 设置连接主机服务器超时时间：15000毫秒
			connection.setConnectTimeout(15000);
			connection.setRequestProperty("User-Agent","Mozilla/5.0 (Windows NT 6.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/64.0.3282.119 Safari/537.36");//电脑级ua
			// 设置读取主机服务器返回数据超时时间：60000毫秒
			connection.setReadTimeout(60000);

			// 默认值为：false，当向远程服务器传送数据/写数据时，需要设置为true
			connection.setDoOutput(true);
			// 默认值为：true，当前向远程服务读取数据时，设置为true，该参数可有可无
			connection.setDoInput(true);
			// 设置传入参数的格式:请求参数应该是 name1=value1&name2=value2 的形式。
			connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
			// 设置鉴权信息：Authorization: Bearer da3efcbf-0845-4fe3-8aba-ee040be542c0
			connection.setRequestProperty("Authorization", "Bearer da3efcbf-0845-4fe3-8aba-ee040be542c0");
			// 通过连接对象获取一个输出流
			os = connection.getOutputStream();
			// 通过输出流对象将参数写出去/传输出去,它是通过字节数组写出的
			os.write(param.getBytes());
			// 通过连接对象获取一个输入流，向远程读取
			if (connection.getResponseCode() == 200)
			{

				is = connection.getInputStream();
				// 对输入流对象进行包装:charset根据工作项目组的要求来设置
				br = new BufferedReader(new InputStreamReader(is, "UTF-8"));

				StringBuffer sbf = new StringBuffer();
				String temp = null;
				// 循环遍历一行一行读取数据
				while ((temp = br.readLine()) != null)
				{
					sbf.append(temp);
					sbf.append("\r\n");
				}
				result = sbf.toString();
			}
		}
		catch (MalformedURLException e)
		{
			e.printStackTrace();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		finally
		{
			// 关闭资源
			if (null != br)
			{
				try
				{
					br.close();
				}
				catch (IOException e)
				{
					e.printStackTrace();
				}
			}
			if (null != os)
			{
				try
				{
					os.close();
				}
				catch (IOException e)
				{
					e.printStackTrace();
				}
			}
			if (null != is)
			{
				try
				{
					is.close();
				}
				catch (IOException e)
				{
					e.printStackTrace();
				}
			}
			// 断开与远程地址url的连接
			connection.disconnect();
		}
		return result;
	}

	public static void download(String urlString, String filename,String savePath,long restTime) throws Exception { 

		Thread.sleep(restTime);

        // 构造URL 

        URL url = new URL(urlString); 

		System.out.println(urlString);
        // 打开连接 

        URLConnection con = url.openConnection(); 

        //设置请求超时为5s 

        con.setConnectTimeout(5*1000); 

        //防止简单反扒

        String referer = url.getProtocol()+"://"+url.getHost();

        con.setRequestProperty("Referer", referer);

        // 输入流 

        InputStream is = con.getInputStream(); 

        // 1K的数据缓冲 

        byte[] bs = new byte[1024]; 

        // 读取到的数据长度 

        int len; 

        // 输出的文件流 

		File sf=new File(savePath); 

		if(!sf.exists()){ 

			sf.mkdirs(); 

		} 

		OutputStream os = new FileOutputStream(sf.getPath()+"/"+filename+".jpg"); //文件类型jpg

        // 开始读取 

        while ((len = is.read(bs)) != -1) { 

			os.write(bs, 0, len); 

        } 

        System.out.println(sf.getPath()+"\\"+filename+"下载完成");

        // 完毕，关闭所有链接 

        os.close(); 

        is.close();
	}
	
}
