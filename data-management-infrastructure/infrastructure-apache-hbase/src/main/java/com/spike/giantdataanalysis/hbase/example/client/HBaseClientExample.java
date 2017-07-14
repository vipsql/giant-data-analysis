/*-
 * [[[LICENSE-START]]]
 * GDA[infrastructure-apache-hbase]
 * ==============================================================================
 * Copyright (C) 2017 zhoujiagen@gmail.com
 * ==============================================================================
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * 
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 * [[[LICENSE-END]]]
 */

package com.spike.giantdataanalysis.hbase.example.client;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.ConnectionFactory;
import org.apache.hadoop.hbase.client.Get;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.client.ResultScanner;
import org.apache.hadoop.hbase.client.Scan;
import org.apache.hadoop.hbase.client.Table;
import org.apache.hadoop.hbase.util.Bytes;

import com.spike.giantdataanalysis.hbase.support.HBases;

/**
 * <pre>
 * HBaseClient示例
 * 
 * REF: docs/apidocs/index.html
 * 
 * PRE: 
 * $ create 'testtable', 'colfam1'
 * 
 * RESULT:
 * $ scan 'testtable'
 * </pre>
 * @author zhoujiagen
 * @since 2016年10月16日 下午10:37:48
 */
public class HBaseClientExample {

  public static void main(String[] args) throws IOException {

    Configuration conf = HBases.loadDefaultConfiguration();

    Connection connection = ConnectionFactory.createConnection(conf);

    try {

      Table table = connection.getTable(TableName.valueOf("testtable"));
      try {
        // 1 Put
        Put p = new Put(Bytes.toBytes("row1"));
        p.addColumn(Bytes.toBytes("colfam1"), Bytes.toBytes("qual1"), Bytes.toBytes("val1"));

        table.put(p);

        // 2 Get
        Get g = new Get(Bytes.toBytes("row1"));
        Result r = table.get(g);
        byte[] value = r.getValue(Bytes.toBytes("colfam1"), Bytes.toBytes("qual1"));
        String valueStr = Bytes.toString(value);
        System.out.println("GET: " + valueStr);

        // 3 Scan
        Scan s = new Scan();
        s.addColumn(Bytes.toBytes("colfam1"), Bytes.toBytes("qual1"));
        ResultScanner scanner = table.getScanner(s);
        try {
          for (Result rr = scanner.next(); rr != null; rr = scanner.next()) {
            System.out.println("Found row: " + rr);
          }

          // The other approach is to use a foreach loop. Scanners are
          // iterable!
          // for (Result rr : scanner) {
          // System.out.println("Found row: " + rr);
          // }
        } finally {
          scanner.close();
        }

        // Close your table and cluster connection.
      } finally {
        if (table != null) table.close();
      }
    } finally {
      connection.close();
    }
  }
}
