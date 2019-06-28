
# 短学期实践项目java后台

## 接口文档
### 登陆
> 路由：/auth/login

> 方法：post

> 参数： 
>* username | 用户名
>* password | 密码

### 注册 
> 路由：/auth

> 方法： post

> 参数
>* username | 用户名
>* password | 密码
>* nickname | 昵称

### 检查用户名是否可用
> 路由： /auth

> 方法： get

> 参数：
>* username | 用户名

### 下载
> 路由：/down/{id}/{uuid}

> 方法：get

> 参数：
>* id | 文件的id
>* uuid | 文件的uuid

> 返回值：返回文件

### 获取文件夹
> 路由：/folder

> 方法：get

> 参数
>* userId | 用户的id

> 返回值：获取到的文件夹

### 获取文件
> 路由：/file

> 方法：get

> 参数
>* fileId | 文件id
>* password | 若文件夹有密码要这个参数

> 返回值：文件的数组

### 获取博客
> 路由：/blog

> 方法：get

> 参数：
>* userId | 被获取博客的用户的id

> 返回值：博客类型的数组

### 获取评论
> 路由：/comment

> 方法：get

> 参数：
>* blogId | 获取评论的所属博客id

> 返回值：包含评论类型的数组

### 获取点赞
> 路由：/like

> 方法：get

> 参数：
>* blogId | 获取点赞的所属博客id

> 返回值：包含对该博客点赞的用户的数组

### 粉丝
> 路由：/fans

> 方法：get

> 参数：
>* userId | 用户id

> 返回值：包含所有关注该id用户的数组

### 关注
> 路由：/focus

> 方法：get

> 参数：
>* userId | 用户id

> 返回值：包含该用户id所有关注过的用户的数组

### 更新用户信息
> 路由：/admin/auth

> 方法：post

> 参数：
>* nickname | 昵称
>* sex | 性别
>* sign | 签名
>* profilePicture | 头像
>* brith | 生日（毫秒数）

### 更改密码
> 路由：/admin/auth

> 方法：put

> 参数：
>* password | 密码
>* oldPassword | 旧密码

### 获取自己的用户信息
> 路由：/admin/auth

> 方法：get 

>返回值：包含有自己信息的用户类型的数据

### 新增博客
> 路由：/admin/blog

> 方法：post

> 参数：
>* content | 文字内容

### 删除博客
> 路由：/admin/blog

> 方法：delete

> 参数：
>* blogId | 要删除的博客的id

### 更新博客
> 路由：/admin/blog

> 方法：put

> 参数：
>* content | 新的文章内容
>* blogId | 博客id

### 增加评论
> 路由：/admin/comment

> 方法：post

> 参数：
>* content | 评论内容
>* blogId | 要评论的博客的id

### 删除评论
> 路由：/admin/comment

> 方法：delete

> 参数：
>* commentId | 评论id

### 点赞
> 路由：/admin/like

> 方法：post

> 参数：
>* blogId | 要点赞的博客的id

### 关注
> 路由：/admin/focus

> 方法：post

> 参数：
>* id | 要关注的用户的id

### 取关
> 路由：/admin/focus

> 方法：delete

> 参数：
>* id | 要取关的用户的id

### 创建文件夹
> 路由：/admin/floder

> 方法：post

> 参数：
>* name | 文件夹名
>* type | 文件夹类型（0为公开的，1为私有的，2为加密的）
>* password | 文件夹密码（若文件加密才需要）

### 获取所有自己的文件夹
> 路由：/admin/floder

> 方法：get

> 返回值：包含所有自己文件夹类型的数组

### 获取自己该文件夹下的所有文件
> 路由：/admin/file

> 方法：get

> 参数
>* folderId | 文件夹id

> 返回值：文件类型的数组

### 上传文件
> 路由：/admin/file

> 方法：post

> 参数
>* file | 文件
>* foleId | 文件夹id

### 删除文件
> 路由：/admin/file

> 方法：delete

> 参数
>* id | 要删除的文件的id

