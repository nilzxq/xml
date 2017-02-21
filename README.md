# xml
http://www.imooc.com/video/3659
# xml 解析
http://www.imooc.com/video/3659

4种解析方式对比：DOM/SAX/JDOM/DOM4J<br>
基础方法：DOM、SAX<br>
DOM：平台无关的官方解析方式<br>
SAX：基于事件驱动的解析方式<br>
扩展方法：JDOM、DOM4J（在基础的方法上扩展出来的，只有Java中能够使用的解析方法）<br>
<br><br>
DOM：<br>
一次性将xml所有内容置于内存中，并形成一颗倒树。（对于内存要求较高）<br>
优点：形成了树结构，直观好理解，代码更容易编写；解析过程中树结构保留在内存中，方便修改。<br>
缺点：当xml文件较大时，对内存耗费比较大，容易影响解析性能并造成内存溢出。<br>
SAX：基于事件驱动的解析方式<br>
是逐条运行的，会根据当前内容判断应该采用什么方法。<br>
优点：采用事件驱动模式，对内存耗费比较小；适用于只需要处理xml中数据而不考虑结构时使用。<br>
缺点：不易编码；很难同时访问同一个xml中的多处不同数据。<br>
JDOM：仅使用具体类而不使用接口，限制了灵活性；API大量使用了Collection类，对集合类较熟悉时，较方便。<br>
DOM4J：是JDOM的一种智能分支，它合并了许多超出基本xml文档表示的功能；DOM4J使用接口和抽象基本类方法，是一个优秀的JAVA XML API；具有性能优异、灵活性好、功能强大和极端易用使用的特点；是一个开放源代码的软件。性能比JDOM更高。<br>
<br>
<br>
JUnit是Java提供的一种进行单元测试的自动化工具。测试方法可以写在任意类中的任意位置。使用JUnit可以没有main()入口进行测试。<br>
DOM4J在灵活性和对复杂xml的支持上都要强于DOM<br>
DOM4J的应用范围非常的广，例如在三大框架的Hibernate中是使用DOM4J的方式解析文件的。<br>
DOM是w3c组织提供的一个官方解析方式，在一定程度上是有所应用的。<br>
当XML文件比较大的时候，会发现DOM4J比较好用<br>
1.JUnit：Java提供的单元测试；@Test注解；采用JUnit不需要程序入口main方法<br>
2.性能测试结果：几kB的xml文件；建议使用DOM4J解析<br>
DOM-33ms<br>
SAX-6ms<br>
JDOM-69ms<br>
DOM4J-45ms<br>
工程右键build path --Add library--JUnit单元测试 --version:JUnit4<br>
DOM:33,SAX:6<br>
JDOM:69;DOM4J:45<br>
DOM 有可能溢出<br>
多使用DOM4J<br>
<br><br>
SAX基于事件，DOM基于树，JDOM&DOM4J基于底层API<br>
如果要频繁修改选择DOM方式，否则选择SAX（速率较快）。<br>
    假设我现在要生成一个xml。让它去存储书籍信息。首先写根节点bookstore，然后添加第一本书，然后添加第二本书，<br>
    这时候发现第一本书中有某一个内容忘记添加了，想回去进行修改或增加。这时候我们发现SAX解析是无法做到这一点的，因为SAX解析是基于事件的。<br>
    基于事件的模型呢，把某一个标签走完了之后，它是不能走回头路的，它没有状态性可言。<br>
    所以在使用DOM还是SAX进行选择时，要考虑到你是不是要对生成的模型进行频繁的修改。<br>
    如果不是呢，建议选用SAX，因为SAX的性能也是非常高的。<br>
    <br><br>
生成xml的四种方式的性能对比：SAX > DOM4J > JDOM > DOM<br>
在一个课程测试用例中四种方式的用时(Ms)：<br>
DOM：418<br>
SAX：3<br>
JDOM：158<br>
DOM4J：59<br><br>
DOM/SAX官方自带：<br>

DOM生成DOM树加载到内存，方便修改，性能慢。<br>

SAX性能最高，但是不能返回修改便签中的内容<br>

DOM4J相对综合最好，应用广泛<br>
