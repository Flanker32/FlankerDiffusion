# 多Agent扩散模型

## 包结构
### Agent包
这个包主要是与agent相关的对象，工厂等

determine包：包括agent的四种决策模型，所有模型实现AgentDetermineStragy接口

Agent类：agent类，保存节点的阈值，权重，前驱后继节点等信息，被Network类调用。设计agent的id为int型整数

AgentFactory:用于生成Agent的工厂，主要提供newAgent方法传入AgentParameter对象，根据对象内容生成相应Agent，同时提供传入具体参数的同名重载方法

StrategyType：Agent策略类型枚举类，主要作为AgentParameter和NetworkParameter中的参数

### Bean包
各种数据对象

AgentData类：agent数据对象，数据生成时生成数据对象，然后根据数据对象生成AgentParameter，最后由工厂生成对应Agent（感觉有点多余，应该会在之后的版本中修改掉）

AgentParameter：agent参数对象，agentFactory根据参数对象的内容生成Agent（其实就是把参数列表打包到对象里）

DiffusionResult：扩散结果，包括传播轮数，激活节点书，每轮激活节点数，节点最终激活状态等信息

EdgeData类：边数据对象，数据生成时生成数据对象，然后根据数据对象在network中添加边

NetworkData类：把AgentData和EdgeData包进一个对象…………

NetworkParameter：网络参数对象，NetworkFactory根据参数生成相应网络

### Experiment包
自己之前做的相应实验……包括方法测试啥的，暂时可以无视

### Network包
Agent网络包

datagenerator包：网络数据生成包，主要是赵振的代码移植过来的，NetworkDataFactory类对外提供服务，对外提供generate方法，传入NetworkParameter，返回相应参数的网络数据对象（NetworkData）

Network类：网络实现类，主要提供startDiffusion方法，传入int数组类型开始节点列表（也可以是double型的初始激活比例），初始数据和最大轮数，返回扩散结果对象。同时提供changeAgentWeight，changeAgentThreshold方法根据正态分布重新生成所有节点的权重、阈值；changeAgentDetermineStragy，修改所有节点的决策策略
PS ：calSimilarityPoints是传入节点id，返回所有节点，并按照跟传入节点的相似度排序，这个方法我还没测试…………使用须谨慎

NetworkFactory：网络生成类，主要提供三种方法：

1. generateNetwork，传入NetworkPatameter根据赵振的代码生成网络
2. readSouthEaseNetworkFromFile 负责生成东南大学数据对应的网络，传入节点数，最大阈值，数据文件名，网络参数对象（只需设置策略和是否二值）生成网路
3. readNetworkFromFile 负责根据高凯程序生成的数据生成对应网络，传入点文件名，边文件名，网络参数对象（只需设置策略和是否二值）

NetworkType：网络类型枚举类

### Constant类：
常量类，设计放些全局参数什么的，然而并没有用到…………
### Enterence类：
程序入口
### IOHelper类：
IO相关静态方法
### Util类：
工具类，目前就是生成符合正态分布的随机数……

###数据文件
cluster random scalefree smallworld是东南大学实验用的数据，分别是高聚类，随机，无标度和小世界网络，节点数都是30000

## 实验说明
跑实验的话一般只需要在Enterence中调用NetworkFactory生成相应网络，再执行Network的网络传播方法，根据返回结果分析就好，如果需要其他功能可以联系我修改~