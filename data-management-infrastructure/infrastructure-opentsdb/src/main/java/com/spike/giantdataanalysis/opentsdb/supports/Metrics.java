package com.spike.giantdataanalysis.opentsdb.supports;

import java.util.Date;
import java.util.Map;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.collect.Maps;
import com.spike.giantdataanalysis.opentsdb.supports.OpenTSDBs.AbstractMetricPoint;
import com.spike.giantdataanalysis.opentsdb.supports.OpenTSDBs.MetricPointBuilder;
import com.spike.giantdataanalysis.opentsdb.supports.PerformanceIndex.OperationSystem.CPU;
import com.spike.giantdataanalysis.opentsdb.supports.PerformanceIndex.OperationSystem.Disk;
import com.spike.giantdataanalysis.opentsdb.supports.PerformanceIndex.OperationSystem.DiskIO;
import com.spike.giantdataanalysis.opentsdb.supports.PerformanceIndex.OperationSystem.Kernel;
import com.spike.giantdataanalysis.opentsdb.supports.PerformanceIndex.OperationSystem.Network;
import com.spike.giantdataanalysis.opentsdb.supports.PerformanceIndex.OperationSystem.Process;
import com.spike.giantdataanalysis.opentsdb.supports.PerformanceIndex.OperationSystem.Sys;

public class Metrics {
    private static final Logger LOG = LoggerFactory.getLogger(Metrics.class);

    /**
     * <pre>
     * 展示metric
     * 
     * CPU
     * Disk
     * DiskIO
     * Network
     * Kernel
     * Sys
     * Process
     * </pre>
     * @param args
     */
    public static void main(String[] args) {
        for (CPU e : CPU.values()) {
            System.out.println("tsdb mkmetric " + e.getMetric());
        }
        for (Disk e : Disk.values()) {
            System.out.println("tsdb mkmetric " + e.getMetric());
        }
        for (DiskIO e : DiskIO.values()) {
            System.out.println("tsdb mkmetric " + e.getMetric());
        }
        for (Network e : Network.values()) {
            System.out.println("tsdb mkmetric " + e.getMetric());
        }
        for (Kernel e : Kernel.values()) {
            System.out.println("tsdb mkmetric " + e.getMetric());
        }
        for (Sys e : Sys.values()) {
            System.out.println("tsdb mkmetric " + e.getMetric());
        }
        for (Process e : Process.values()) {
            System.out.println("tsdb mkmetric " + e.getMetric());
        }
    }

    public static enum MetricType {
        INT, PERCENT, DOUBLE
    }

    public static final String TAG_DATACENTER = "dc";
    public static final String[] TAG_DATACENTER_VALUES = //
            new String[] {"dc001", "dc002", "dc003", "dc004"};

    public static final String TAG_MACHINE_GROUP = "mg";
    public static String[] TAG_MACHINE_GROUP_VALUES = //
            new String[] {"mg0001", "mg0002", "mg0003", "mg0004", "mg0005",//
                    "mg0006", "mg0007", "mg0008", "mg009", "mg0010"};

    public static final String TAG_MACHINE = "m";
    public static final String[] TAG_MACHINE_VALUES = //
            new String[] {"m00001", "m00002", "m00003", "m00004", "m00005",//
                    "m00006", "m00007", "m00008", "m00009", "m00010",//
                    "m00011", "m00012", "m00013", "m00014", "m00015",//
                    "m00016", "m00017", "m00018", "m00019", "m00020"};


    public static double DEFAULT_MAX_DOUBLE = 1000d;
    public static int DEFAULT_MAX_INT = 1000;
    public static float DEFAULT_MAX_FLOAT = 1000f;


    public static int METRIC_SIZE() {
        return 7;
    }

    /**
     * <pre>
     * 0 cpu
     * 1 disk
     * 2 diskio
     * 3 kernel
     * 4 network
     * 5 process
     * 6 sys
     * </pre>
     */
    public static AbstractMetricPoint RND_METRICPOINT() {
        int rndInt = Metrics.INT(METRIC_SIZE());
        AbstractMetricPoint result = null;
        if (rndInt == 0) {
            result = cpu();
        } else if (rndInt == 1) {
            result = disk();
        } else if (rndInt == 2) {
            result = diskio();
        } else if (rndInt == 3) {
            result = kernel();
        } else if (rndInt == 4) {
            result = network();
        } else if (rndInt == 5) {
            result = process();
        } else if (rndInt == 6) {
            result = sys();
        } else {
            LOG.error("RND_METRICPOINT failed: invalid metric index");
        }

        if (result != null) {
            result.setTimestamp(new Date().getTime());

            Map<String, String> tags = Maps.newHashMap();
            tags.put(TAG_DATACENTER, TAG_DATACENTER_VALUES[INT(TAG_DATACENTER_VALUES.length)]);
            tags.put(TAG_MACHINE_GROUP, TAG_MACHINE_GROUP_VALUES[INT(TAG_MACHINE_GROUP_VALUES.length)]);
            tags.put(TAG_MACHINE, TAG_MACHINE_VALUES[INT(TAG_MACHINE_VALUES.length)]);
            result.setTags(tags);
        }

        return result;
    }



    public static AbstractMetricPoint cpu() {
        int size = CPU.values().length;
        int rndIndex = Metrics.INT(size);
        CPU e = CPU.E(rndIndex);

        if (Metrics.MetricType.PERCENT.equals(e.getType())) {
            MetricPointBuilder builder = new MetricPointBuilder(Metrics.PRECENT());
            builder.metric(e.getMetric());
            return builder.build();
        } else if (Metrics.MetricType.INT.equals(e.getType())) {
            MetricPointBuilder builder = new MetricPointBuilder(Metrics.INT(Metrics.DEFAULT_MAX_INT));
            builder.metric(e.getMetric());
            return builder.build();
        } else {
            // should not touch here
            return null;
        }
    }

    public static AbstractMetricPoint disk() {
        int size = Disk.values().length;
        int rndIndex = Metrics.INT(size);
        Disk e = Disk.E(rndIndex);

        if (Metrics.MetricType.PERCENT.equals(e.getType())) {
            MetricPointBuilder builder = new MetricPointBuilder(Metrics.PRECENT());
            builder.metric(e.getMetric());
            return builder.build();
        } else if (Metrics.MetricType.INT.equals(e.getType())) {
            MetricPointBuilder builder = new MetricPointBuilder(Metrics.INT(Metrics.DEFAULT_MAX_INT));
            builder.metric(e.getMetric());
            return builder.build();
        } else {
            // should not touch here
            return null;
        }
    }

    public static AbstractMetricPoint diskio() {
        int size = DiskIO.values().length;
        int rndIndex = Metrics.INT(size);
        DiskIO e = DiskIO.E(rndIndex);

        if (Metrics.MetricType.PERCENT.equals(e.getType())) {
            MetricPointBuilder builder = new MetricPointBuilder(Metrics.PRECENT());
            builder.metric(e.getMetric());
            return builder.build();
        } else if (Metrics.MetricType.INT.equals(e.getType())) {
            MetricPointBuilder builder = new MetricPointBuilder(Metrics.INT(Metrics.DEFAULT_MAX_INT));
            builder.metric(e.getMetric());
            return builder.build();
        } else {
            // should not touch here
            return null;
        }
    }

    public static AbstractMetricPoint network() {
        int size = Network.values().length;
        int rndIndex = Metrics.INT(size);
        Network e = Network.E(rndIndex);

        if (Metrics.MetricType.PERCENT.equals(e.getType())) {
            MetricPointBuilder builder = new MetricPointBuilder(Metrics.PRECENT());
            builder.metric(e.getMetric());
            return builder.build();
        } else if (Metrics.MetricType.INT.equals(e.getType())) {
            MetricPointBuilder builder = new MetricPointBuilder(Metrics.INT(Metrics.DEFAULT_MAX_INT));
            builder.metric(e.getMetric());
            return builder.build();
        } else {
            // should not touch here
            return null;
        }
    }

    public static AbstractMetricPoint kernel() {
        int size = Kernel.values().length;
        int rndIndex = Metrics.INT(size);
        Kernel e = Kernel.E(rndIndex);

        if (Metrics.MetricType.INT.equals(e.getType())) {
            MetricPointBuilder builder = new MetricPointBuilder(Metrics.INT(Metrics.DEFAULT_MAX_INT));
            builder.metric(e.getMetric());
            return builder.build();
        } else {
            // should not touch here
            return null;
        }
    }

    public static AbstractMetricPoint sys() {
        int size = Sys.values().length;
        int rndIndex = Metrics.INT(size);
        Sys e = Sys.E(rndIndex);

        if (Metrics.MetricType.INT.equals(e.getType())) {
            MetricPointBuilder builder = new MetricPointBuilder(Metrics.INT(Metrics.DEFAULT_MAX_INT));
            builder.metric(e.getMetric());
            return builder.build();
        } else {
            // should not touch here
            return null;
        }
    }

    public static AbstractMetricPoint process() {
        int size = Process.values().length;
        int rndIndex = Metrics.INT(size);
        Process e = Process.E(rndIndex);

        if (Metrics.MetricType.PERCENT.equals(e.getType())) {
            MetricPointBuilder builder = new MetricPointBuilder(Metrics.PRECENT());
            builder.metric(e.getMetric());
            return builder.build();
        } else if (Metrics.MetricType.INT.equals(e.getType())) {
            MetricPointBuilder builder = new MetricPointBuilder(Metrics.INT(Metrics.DEFAULT_MAX_INT));
            builder.metric(e.getMetric());
            return builder.build();
        } else {
            // should not touch here
            return null;
        }
    }



    public static long LONG() {
        Random rnd = new Random(System.currentTimeMillis());
        return rnd.nextLong();
    }


    /**
     * @return [0.0d, 1.0d)
     */
    public static double DOUBLE() {
        Random rnd = new Random(System.currentTimeMillis());
        return rnd.nextDouble();
    }

    /**
     * @return [0.0d, max)
     */
    public static double DOUBLE(double max) {
        Random rnd = new Random(System.currentTimeMillis());
        double result = rnd.nextDouble() * max;
        return result;
    }

    /**
     * @return [0.0f, 1.0f)
     */
    public static float FLOAT() {
        Random rnd = new Random(System.currentTimeMillis());
        return rnd.nextFloat();
    }

    /**
     * @return [0.0f, max)
     */
    public static float FLOAT(float max) {
        Random rnd = new Random(System.currentTimeMillis());
        return rnd.nextFloat();
    }

    public static int INT() {
        Random rnd = new Random(System.currentTimeMillis());
        return rnd.nextInt();
    }

    /**
     * @return [0, max)
     */
    public static int INT(int max) {
        Random rnd = new Random(System.currentTimeMillis());
        return rnd.nextInt(max);
    }

    /**
     * @return 例50.2
     */
    public static float PRECENT() {
        Random rnd = new Random(System.currentTimeMillis());
        float result = rnd.nextFloat() * 101f;
        if (result > 100f) result = 100f;
        return result;
    }

    /**
     * @return [0.0f, max)
     */
    public static float PERCENT(float max) {
        Random rnd = new Random(System.currentTimeMillis());
        return rnd.nextFloat() * max;
    }

}
