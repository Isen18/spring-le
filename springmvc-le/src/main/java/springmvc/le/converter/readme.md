### 一、自定义PropertyEditor
###### 编写自定义PropertyEditor
* CustomDatePropertyEditor
* 参考org.springframework.beans.propertyeditors.CustomDateEditor

###### 配置
* 方式1：@InitBinder + WebDataBinder
* 方式2：在xml中配置FormattingConversionServiceFactoryBean

### 二、自定义Converter
* CustomDateConverter
* 参考org.springframework.core.convert.support.StringToBooleanConverter

###### 配置
* 方式1：@InitBinder + WebDataBinder
* 方式2：xml + FormattingConversionServiceFactoryBean + <mvc:annotation-driven/>

### 三、自定义Formatter
* 
* 参考org.springframework.format.datetime.DateFormatter

###### 配置
* 方式2：xml + FormattingConversionServiceFactoryBean + <mvc:annotation-driven/>

### 四、自定义HttpMessageConverter

