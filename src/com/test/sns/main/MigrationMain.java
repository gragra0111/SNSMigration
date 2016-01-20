package com.test.sns.main;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import com.test.sns.controller.ArticleController;
import com.test.sns.controller.ArticleFileController;
import com.test.sns.controller.ArticleLikeController;
import com.test.sns.controller.ArticleReplyController;
import com.test.sns.controller.ArticleScopeController;
import com.test.sns.controller.BookmarkArticleController;
import com.test.sns.controller.BookmarkController;
import com.test.sns.controller.DeptController;
import com.test.sns.controller.FileController;
import com.test.sns.controller.GroupController;
import com.test.sns.controller.GroupMemberController;
import com.test.sns.controller.HashtagController;
import com.test.sns.controller.MessageController;
import com.test.sns.controller.UserCareerController;
import com.test.sns.controller.UserController;
import com.test.sns.controller.UserDeptController;
import com.test.sns.controller.UserLinkController;
import com.test.sns.controller.UserMessageController;

public class MigrationMain {
	public static void main(String[] args) {
		ApplicationContext context = new GenericXmlApplicationContext("applicationContext.xml");
		
		UserController userController = new UserController(context);
		
		//GroupController groupController = new GroupController(context);
		
		//DeptController deptController = new DeptController(context);
		
		//UserDeptController userDeptController = new UserDeptController(context);
		
		//GroupMemberController groupMemberController = new GroupMemberController(context);
		
		//UserLinkController userLinkController = new UserLinkController(context);
		
		//BookmarkController bookmarkController = new BookmarkController(context);
		
		//MessageController messageController = new MessageController(context);
		
		//UserMessageController userMessageController = new UserMessageController(context);
		
		//FileController fileController = new FileController(context);
		
		//ArticleController articleController = new ArticleController(context);
		
		//ArticleFileController articleFileController = new ArticleFileController(context);
		
		//ArticleScopeController articleScopeController = new ArticleScopeController(context);
		
		//HashtagController hashtagController = new HashtagController(context);
		
		//ArticleReplyController articleReplyController = new ArticleReplyController(context);
		
		//ArticleLikeController articleLikeController = new ArticleLikeController(context);
		
		//BookmarkArticleController bookmarkArticleController = new BookmarkArticleController(context);
		
		//UserCareerController userCareerController = new UserCareerController(context);
		
	}
}
