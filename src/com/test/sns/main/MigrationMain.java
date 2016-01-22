package com.test.sns.main;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

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

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="/applicationContext.xml")
public class MigrationMain {
	@Autowired
	private UserController userController;
	@Autowired 
	private GroupController groupController;
	@Autowired 
	private DeptController deptController;
	@Autowired 
	private UserDeptController userDeptController;
	@Autowired 
	private GroupMemberController groupMemberController;
	@Autowired 
	private UserLinkController userLinkController;
	@Autowired 
	private BookmarkController bookmarkController;
	@Autowired 
	private MessageController messageController;
	@Autowired 
	private UserMessageController userMessageController;
	@Autowired 
	private FileController fileController;
	@Autowired
	private ArticleController articleController;
	@Autowired 
	private ArticleFileController articleFileController;
	@Autowired 
	private ArticleScopeController articleScopeController;
	@Autowired 
	private HashtagController hashtagController;
	@Autowired 
	private ArticleReplyController articleReplyController;
	@Autowired 
	private ArticleLikeController articleLikeController;
	@Autowired 
	private BookmarkArticleController bookmarkArticleController;
	@Autowired 
	private UserCareerController userCareerController;
	
	@Test
	public void doMigration() {
		userController.userTableMigration();
		groupController.groupTableMigration();
		deptController.deptTableMigration();
		userDeptController.userDeptTableMigration();
		groupMemberController.groupMemberTableMigration();
		userLinkController.userLinkTableMigration();
		bookmarkController.bookmarkTableMigration();
		messageController.messageTableMigration();
		userMessageController.userMessageTableMigration();
		fileController.fileTableMigration();
		articleController.articleTableMigration();
		articleFileController.articleFileTableMigration();
		articleScopeController.articleScopeTableMigration();
		hashtagController.hashtagTableMigration();
		hashtagController.articleHashtagTableMigration();
		articleReplyController.articleReplyTableMigration();
		articleLikeController.articleLikeTableMigration();
		bookmarkArticleController.bookmarkArticleTableMigration();
		userCareerController.userCareerMigration();
	}

}
