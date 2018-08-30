package com.bitkeep.sdk;

import com.bitkeep.sdk.constant.BitKeepConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import static com.bitkeep.sdk.constant.BitKeepConstants.BK_CLIENT_ID;
import static com.bitkeep.sdk.constant.BitKeepConstants.BK_SECRET;

/**
 * 配置文件
 *
 * @author : Hui.Wang [huzi.wh@gmail.com]
 * @version : 1.0
 * @created on  : 2018/8/3
 */
public class BitkeepConf implements BitKeepConstants {
    static final Logger LOG = LoggerFactory.getLogger(BitkeepConf.class);

    /**
     *
     */
    private Properties _conf = new Properties();

    private File file;

    private String secretKey;

    private String clientId;

    private InputStream in;

    private Properties props;

    public BitkeepConf with(String clientId, String secretKey) {
        this.clientId = clientId;
        this.secretKey = secretKey;
        return this;
    }

    /**
     *
     * @param file
     *            absolute path of bitkeep.properties
     * @return
     */
    public BitkeepConf with(File file) {
        this.file = file;
        return this;
    }

    /**
     *
     * @param in
     *            InputStream of bitkeep.properties
     * @return
     */
    public BitkeepConf with(InputStream in) {
        this.in = in;
        return this;
    }

    /**
     *
     * @param props
     *            properties of bitkeep.properties
     * @return
     */
    public BitkeepConf with(Properties props) {
        this.props = props;
        return this;
    }

    /**
     * 覆盖次序 props &gt; in &gt; file
     *
     * @return
     */
    public BitkeepConf build() {
        try {
            load(file);
            load(in);
            load(props);

            if (_conf.isEmpty()) {
                LOG.info("load default bitkeep.properties");
                load(BitKeepClient.class.getResourceAsStream("/bitkeep.properties"));
            }
        } catch (Exception e) {
            LOG.warn(e.getMessage(), e.fillInStackTrace());
        } finally {
            file = null;
            in = null;
            props = null;
        }
        LOG.info("secretKey-{} clientId-{} conf-{}", getSecretKey(), getClientId(), _conf.toString());
        return this;
    }

    /**
     *
     * @param file
     *            bitkeep.properties
     * @return
     * @throws IOException
     */
    final BitkeepConf load(File file) throws Exception {
        if (file != null && file.exists()) {
            load(new FileInputStream(file));
        }
        return this;
    }

    /**
     *
     * @param in
     *            InputStream of bitkeep.properties
     * @return
     * @throws IOException
     */
    final BitkeepConf load(InputStream in) throws Exception {
        if (in != null) {
            _conf.load(in);
        }
        return this;
    }

    final BitkeepConf load(Properties props) {
        if (props != null) {
            _conf.clear();
            _conf.putAll(props);
        }
        return this;
    }

    /**
     * 查找顺序: 系统配置-&gt;云片配置-&gt;默认值
     *
     * @param key
     * @param defVal
     * @return
     */
    public String getConf(String key, String defVal) {
        return System.getProperty(key, _conf.getProperty(key, defVal));
    }

    public int getConfInt(String key, String defVal) {
        String v = getConf(key, defVal);
        return Integer.parseInt(v);
    }

    /**
     * @since 1.2.6
     */
    public long getConfLong(String key, String defVal) {
        String v = getConf(key, defVal);
        return Long.parseLong(v);
    }

    /**
     * apiSecretKey优先级:
     * <ol type="1">
     * <li>System.getProperty("yp.secretKey")</li>
     * <li>bitkeep.properties</li>
     * <li>new bitkeepClient(secretKey)</li>
     * </ol>
     */
    public String getSecretKey() {
        return System.getProperty(BK_SECRET, _conf.getProperty(BK_SECRET, secretKey));
    }

    public String getClientId() {
        return System.getProperty(BK_CLIENT_ID, _conf.getProperty(BK_CLIENT_ID, clientId));
    }

    @Override
    public String toString() {
        return "BitkeepConf-" + _conf.toString();
    }

}
