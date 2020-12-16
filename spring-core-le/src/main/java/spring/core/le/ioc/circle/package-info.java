//循环依赖

//1. 构造函数下能不能解决循环依赖?
// 不能. 无法拿到早期bean进行判断是否存在循环依赖, 都是拿到的完整的对象去调用构造函数的
// 能. @Lazy 注入代理,使用时进行完全初始化

//2. 多例的bean下能不解决循环依赖?
// 不能. 多例没有用到缓存,无法判断是否存在循环依赖.

//解决循环依赖
//1. 重新设计bean组件, 破坏循环依赖
//2. 构造函数参数 + @Lazy
//3. 使用setter方式注入
//4. 初始化后,手动注入,比如在@PostConstruct方法里手动注入 或者 ApplicationContextAware + InitializingBean方式
