package testSync;

//import java.util.Arrays;
//import java.util.List;
//
//import org.apache.hadoop.conf.Configuration;
//import org.apache.hadoop.hbase.HBaseConfiguration;
//import org.apache.hadoop.hbase.HColumnDescriptor;
//import org.apache.hadoop.hbase.HTableDescriptor;
//import org.apache.hadoop.hbase.TableName;
//import org.apache.hadoop.hbase.util.Bytes;
//import org.apache.hadoop.hbase.client.*;
//
//public class hbaseTest {
//	
//	    public static void main(String[] args) {
//	        Admin admin = null;
//	        try {
//	            Configuration conf = HBaseConfiguration.create();	// 获取hbase配置文件
//	            admin = new HBaseAdmin(conf); //获得管理员
//	            TableName tn=TableName.valueOf("scores2");
//	            if(!admin.tableExists(tn)){//判断表是否存在
//	            	 System.out.println("要创建的表没有重复，正在创建！");
//		            HTableDescriptor htd=new HTableDescriptor(tn);//创建表结构对象
//		            HColumnDescriptor hcd1=new HColumnDescriptor("cf");//创建列族
//		            htd.addFamily(hcd1);//将列族添加到表结构
//		            admin.createTable(htd);//创建表
//		            System.out.println("创建表成功！");
////	            		 admin.disableTable(tn);//使表失效
////	                admin.deleteTable(tn);//删除该表
////	                System.out.println("你要创建的表已存在，已经将其删除！");
//	            }
//
//	            //向表中插入数据
//	            Put put=new Put(Bytes.toBytes("r1"));
//	            put.addColumn(Bytes.toBytes("cf"),null,Bytes.toBytes("5"));//列族就是列
//	            put.addColumn(Bytes.toBytes("cf"),Bytes.toBytes("art"),Bytes.toBytes("98"));//在列族下面，创建列
//	            put.addColumn(Bytes.toBytes("cf"),Bytes.toBytes("math"),Bytes.toBytes("91"));
//	            HTable table = new HTable(conf, tn);
//	            table.put(put);
//
//	            //向表中批量插入数据
//	            Put put1=new Put(Bytes.toBytes("r2"));
//	            put1.addColumn(Bytes.toBytes("cf"),null,Bytes.toBytes("4"))
//	            .addColumn(Bytes.toBytes("cf"),Bytes.toBytes("art"),Bytes.toBytes("88"))
//	            .addColumn(Bytes.toBytes("cf"),Bytes.toBytes("math"),Bytes.toBytes("85"));
//
//	            Put put2=new Put(Bytes.toBytes("r3"));
//	            put2.addColumn(Bytes.toBytes("cf"),null,Bytes.toBytes("3"))
//	            .addColumn(Bytes.toBytes("cf"),Bytes.toBytes("art"),Bytes.toBytes("77"))
//	            .addColumn(Bytes.toBytes("cf"),Bytes.toBytes("math"),Bytes.toBytes("65"));
//
//	            List<Put> puts= Arrays.asList(put1,put2);//将数组转为集合
//	            HTable table2 = new HTable(conf, tn);
//	            table2.put(puts);
//	            
//	            		System.out.println("结束");
//
////	            //读取操作
////	            //scan
////	            System.out.println("==================scan查询======================");
////	            Scan scan=new Scan();
////	            Table table3=con.getTable(tn);
////	            ResultScanner resultScanner=table3.getScanner(scan);//获得scan结果集
////	            for(Result rs:resultScanner){
////	                List<Cell> cs=rs.listCells();//将到的每一个结果，转成list的形式
////	                for(Cell cell:cs){
////	                    String rowkey=Bytes.toString(CellUtil.cloneRow(cell));//取到行键
////	                    long timestamp=cell.getTimestamp();//取时间戳
////	                    String fname=Bytes.toString(CellUtil.cloneFamily(cell));//取到列族名
////	                    String qualifier=Bytes.toString(CellUtil.cloneQualifier(cell));//取修饰名，即列名
////	                    String value=Bytes.toString(CellUtil.cloneValue(cell)); //取值
////	                    System.out.println("rowkey=="+rowkey+"---timestamp=="+timestamp+"---qualifier=="+fname+"=>"+qualifier+"---value=="+value);
////	                }
////	            }
//
////	        //get查询数据
////	        System.out.println("================================================================");
////	        System.out.println("========================get查询的数据==============================");   
////	        System.out.println("-----------------------取到的该行所有数据----------------------");   
////	        Get get=new Get(Bytes.toBytes("tg"));//指定行
////	        Table table4=con.getTable(tn);
////	        Result rt=table4.get(get);
////	        List<Cell> cs=rt.listCells();
////	        for(Cell cell:cs){
////	            String rowkey=Bytes.toString(CellUtil.cloneRow(cell));//取到行键
////	            long timestamp=cell.getTimestamp();//取时间戳
////	            String fname=Bytes.toString(CellUtil.cloneFamily(cell));//取到列族名
////	            String qualifier=Bytes.toString(CellUtil.cloneQualifier(cell));//取修饰名，即列名
////	            String value=Bytes.toString(CellUtil.cloneValue(cell)); //取值
////	            System.out.println("rowkey=="+rowkey+"---timestamp=="+timestamp+"---qualifier=="+fname+"=>"+qualifier+"---value=="+value);
////	        }
////
////	        System.out.println("====================get取指定行列数据===================");
////	        Get get1=new Get(Bytes.toBytes("tg"));
////	        get1.addColumn(Bytes.toBytes("coures"), Bytes.toBytes("art"));//指定列族和修饰名
////	        Table table5=con.getTable(tn);
////	        Result rt1=table5.get(get1);
////	        List<Cell> cs1=rt1.listCells();
////	        for(Cell cell:cs1){
////	            String rowkey=Bytes.toString(CellUtil.cloneRow(cell));//取到行键
////	            long timestamp=cell.getTimestamp();//取时间戳
////	            String fname=Bytes.toString(CellUtil.cloneFamily(cell));//取到列族名
////	            String qualifier=Bytes.toString(CellUtil.cloneQualifier(cell));//取修饰名，即列名
////	            String value=Bytes.toString(CellUtil.cloneValue(cell)); //取值
////	            System.out.println("rowkey=="+rowkey+"---timestamp=="+timestamp+"---qualifier=="+fname+"=>"+qualifier+"---value=="+value);
////	        }
////
////	        //删除数据
////	        System.out.println("********************************************************");
////	        System.out.println("******************************删除数据******************************");
////	        System.out.println("删除tg2这行所有数据");
////	        Delete delete=new Delete(Bytes.toBytes("tg2"));
////	        Table table6=con.getTable(tn);
////	        table6.delete(delete);
////	        System.out.println("删除tg3的art列");
////	        Delete delete1=new Delete(Bytes.toBytes("tg3"));
////	        delete1.addColumn(Bytes.toBytes("coures"), Bytes.toBytes("art"));
////	        Table table7=con.getTable(tn);
////	        table7.delete(delete1);
////
////	        System.out.println("========================================================");
////	        System.out.println("==================删除操作后的scan查询======================");
////	        Scan scan1=new Scan();
////	        Table table8=con.getTable(tn);
////	        ResultScanner resultScanner1=table8.getScanner(scan1);//获得scan结果集
////	        for(Result rs:resultScanner1){
////	            List<Cell> cs2=rs.listCells();//将到的每一个结果，转成list的形式
////	            for(Cell cell:cs2){
////	                String rowkey=Bytes.toString(CellUtil.cloneRow(cell));//取到行键
////	                long timestamp=cell.getTimestamp();//取时间戳
////	                String fname=Bytes.toString(CellUtil.cloneFamily(cell));//取到列族名
////	                String qualifier=Bytes.toString(CellUtil.cloneQualifier(cell));//取修饰名，即列名
////	                String value=Bytes.toString(CellUtil.cloneValue(cell)); //取值
////	                System.out.println("rowkey=="+rowkey+"---timestamp=="+timestamp+"---qualifier=="+fname+"=>"+qualifier+"---value=="+value);
////	            }
////	        }
//
//
//	            
//
//	            if (admin != null){
//	                admin.close();
//	            }
//	            
//	        } catch (Exception e) {
//	            e.printStackTrace();
//	        }
//	    }
//	}




import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.Cell;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.HColumnDescriptor;
import org.apache.hadoop.hbase.HTableDescriptor;
import org.apache.hadoop.hbase.KeyValue;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.ConnectionFactory;
import org.apache.hadoop.hbase.client.Delete;
import org.apache.hadoop.hbase.client.Get;
import org.apache.hadoop.hbase.client.HBaseAdmin;
import org.apache.hadoop.hbase.client.HConnection;
import org.apache.hadoop.hbase.client.HConnectionManager;
import org.apache.hadoop.hbase.client.HTable;
import org.apache.hadoop.hbase.client.HTablePool;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.client.ResultScanner;
import org.apache.hadoop.hbase.client.Scan;
import org.apache.hadoop.hbase.client.Table;
import org.apache.hadoop.hbase.client.replication.ReplicationAdmin;
import org.apache.hadoop.hbase.filter.ColumnPrefixFilter;
import org.apache.hadoop.hbase.filter.Filter;
import org.apache.hadoop.hbase.filter.FilterList;
import org.apache.hadoop.hbase.filter.PageFilter;
import org.apache.hadoop.hbase.filter.SingleColumnValueFilter;
//import org.apache.hadoop.hbase.replication.ReplicationEndpoint;
import org.apache.hadoop.hbase.replication.ReplicationException;
import org.apache.hadoop.hbase.replication.ReplicationPeerConfig;
import org.apache.hadoop.hbase.util.Bytes;
import org.apache.hadoop.hbase.zookeeper.ZKUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;


public class hbaseTest {
	  private static Configuration cfg;
    private static Connection connection;
    

    static {
        cfg = HBaseConfiguration.create();
        cfg.addResource("hbase-site.xml");
        try {
            connection = ConnectionFactory.createConnection(cfg);
        	//connection = HConnectionManager.createConnection(cfg);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
  //查看已有表  
    public static List<String> listTables() throws IOException {  
    	List<String> list=new ArrayList<String>();
    	
    	HBaseAdmin admin = (HBaseAdmin) new HBaseAdmin(connection);
        HTableDescriptor hTableDescriptors[] = admin.listTables(); 
        
        for(HTableDescriptor hTableDescriptor :hTableDescriptors){  
            list.add(hTableDescriptor.getNameAsString());
        }  
        
        return list;
    }  

    //新建表
    public static boolean create(String tableName, String columnFamily) throws Exception {
        //HBaseAdmin admin = (HBaseAdmin) new HBaseAdmin(cfg); //connection.getAdmin();
        HBaseAdmin admin = (HBaseAdmin) connection.getAdmin();  

        if (admin.tableExists(tableName)) {
            return false;
        } else {
            String[] columnFamilyArray = columnFamily.split(",");
            HColumnDescriptor[] hColumnDescriptor = new HColumnDescriptor[columnFamilyArray.length];
            for (int i = 0; i < hColumnDescriptor.length; i++) {
                hColumnDescriptor[i] = new HColumnDescriptor(columnFamilyArray[i]);
            }
            
            HTableDescriptor tableDesc = new HTableDescriptor(TableName.valueOf(tableName));
            
            for (HColumnDescriptor columnDescriptor : hColumnDescriptor) {
            	tableDesc.addFamily(columnDescriptor);
            }
            //HTableDescriptor tableDesc = new HTableDescriptor(TableName.valueOf(tableName), familyDesc);

            admin.createTable(tableDesc);
            return true;
        }
    }

    //插入数据
 
    public static boolean put(String tablename, String row, String columnFamily,
                              String qualifier, String data) throws Exception {
        HTable table = (HTable) connection.getTable(TableName.valueOf(tablename));
        Put put = new Put(Bytes.toBytes(row));
        put.addColumn(Bytes.toBytes(columnFamily), Bytes.toBytes(qualifier),Bytes.toBytes(data));
        table.put(put);
        return true;
    }
  public static void main(String[] args) throws Exception {
	put("xlx", "r4", "cf", "col", "v4");
	System.err.println("hello world");
}

    

    
    

    
    

    
        
    }
    
    




