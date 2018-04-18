package com.jsfd.core.web.model;

import com.google.common.collect.Lists;

import java.util.List;

/**
 * 验证结果。
 */
public class ValidateJson {
    /**
     * 验证成功
     */
    private static final Integer OK = 1;
    /**
     * 验证失败
     */
    private static final Integer FAIL = 0;

    private List<Object> results = Lists.newArrayList();

    private ValidateJson() {
    }

    public static ValidateJson newInstance() {
        return new ValidateJson();
    }

    /**
     * 验证失败（使用前台alertTextOk定义的消息）
     * @param fieldId 验证成功的字段名
     */
    public void validateFail(String fieldId) {
        validateFail(fieldId, "");
    }

    /**
     * 验证失败
     * @param fieldId 验证成功的字段名
     * @param message 验证成功时显示的消息
     */
    public void validateFail(String fieldId, String message) {
        results.add(new Object[]{fieldId, FAIL, message});
    }

    /**
     * 验证成功（使用前台alertTextOk定义的消息）
     * @param fieldId 验证成功的字段名
     */
    public void validateSuccess(String fieldId) {
        validateSuccess(fieldId, "");
    }

    /**
     * 验证成功
     * @param fieldId 验证成功的字段名
     * @param message 验证成功时显示的消息
     */
    public void validateSuccess(String fieldId, String message) {
        results.add(new Object[]{fieldId, OK, message});
    }
    
    /**
     * 返回验证结果
     * @return
     */
    public static Object result(String fieldId,boolean isExist) {
    	ValidateJson validateJson = ValidateJson.newInstance();
    	
    	if (isExist) {
			validateJson.validateFail(fieldId);
		} else {
			validateJson.validateSuccess(fieldId);
		}
        
    	if (validateJson.results.size() == 1) {
            return validateJson.results.get(0);
        }
        return validateJson.results;
    }
    
   

}
