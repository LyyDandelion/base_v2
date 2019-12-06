
-- =======================================================================================
-- CODE 菜单编号
-- PCODE  菜单父编号
-- PCODES  当前菜单的所有父菜单编号
-- NAME 菜单名称
-- ICON 菜单图标 
-- URL url地址
-- SORT 菜单排序号
-- LEVELS 菜单层级  MENU_FLAG 是否是菜单 DESCRIPTION 备注 NEW_PAGE_FLAG 是否打开新页面的标识(字典 OPEN_FLAG 是否打开(字典)
-- 脚本名： 用户角色关系
-- 描述：  memberRole
-- =======================================

-- 父操作
INSERT INTO `sys_menu`(CODE, PCODE,PCODES,NAME,ICON,URL,SORT,LEVELS,MENU_FLAG,DESCRIPTION,STATUS,NEW_PAGE_FLAG,OPEN_FLAG,CREATE_TIME,UPDATE_TIME,CREATE_USER,UPDATE_USER)
VALUES
( 'memberRole', 'user_mgr', '[0],[user_mgr],', '用户角色关系', '', '/memberRole', 1200, 2, 'Y', NULL, 'ENABLE', NULL, NULL, now(), NULL, 1, NULL);

-- 子操作 list
INSERT INTO `sys_menu` (CODE, PCODE,PCODES,NAME,ICON,URL,SORT,LEVELS,MENU_FLAG,DESCRIPTION,STATUS,NEW_PAGE_FLAG,OPEN_FLAG,CREATE_TIME,UPDATE_TIME,CREATE_USER,UPDATE_USER)
VALUES
( 'memberRole_list', 'memberRole', '[0],[user_mgr],[memberRole],', '列表', '', '/memberRole/list', 1000, 3, 'N', NULL, 'ENABLE', NULL, NULL,now(), NULL, 1, NULL);

-- 子操作 add
INSERT INTO `sys_menu` (CODE, PCODE,PCODES,NAME,ICON,URL,SORT,LEVELS,MENU_FLAG,DESCRIPTION,STATUS,NEW_PAGE_FLAG,OPEN_FLAG,CREATE_TIME,UPDATE_TIME,CREATE_USER,UPDATE_USER)
VALUES
( 'memberRole_add', 'memberRole', '[0],[user_mgr],[memberRole],', '添加', '', '/memberRole/add', 1200, 3, 'N', NULL, 'ENABLE', NULL, NULL, now(), NULL, 1, NULL);

-- 子操作 detail
INSERT INTO `sys_menu` (CODE, PCODE,PCODES,NAME,ICON,URL,SORT,LEVELS,MENU_FLAG,DESCRIPTION,STATUS,NEW_PAGE_FLAG,OPEN_FLAG,CREATE_TIME,UPDATE_TIME,CREATE_USER,UPDATE_USER)
VALUES
( 'memberRole_detail', 'memberRole', '[0],[user_mgr],[memberRole],', '详情', '', '/memberRole/detail', 1300, 3, 'N', NULL, 'ENABLE', NULL, NULL, now(), NULL, 1, NULL);

-- 子操作 edit
INSERT INTO `sys_menu` (CODE, PCODE,PCODES,NAME,ICON,URL,SORT,LEVELS,MENU_FLAG,DESCRIPTION,STATUS,NEW_PAGE_FLAG,OPEN_FLAG,CREATE_TIME,UPDATE_TIME,CREATE_USER,UPDATE_USER)
VALUES
('memberRole_edit', 'memberRole', '[0],[user_mgr],[memberRole],', '编辑', '', '/memberRole/edit', 1400, 3, 'N', NULL, 'ENABLE', NULL, NULL, now(), NULL, 1, NULL);

-- 子操作 delete
INSERT INTO `sys_menu`(CODE, PCODE,PCODES,NAME,ICON,URL,SORT,LEVELS,MENU_FLAG,DESCRIPTION,STATUS,NEW_PAGE_FLAG,OPEN_FLAG,CREATE_TIME,UPDATE_TIME,CREATE_USER,UPDATE_USER)
VALUES
( 'memberRole_delete', 'memberRole', '[0],[user_mgr],[memberRole],', '删除', '', '/memberRole/delete', 1500, 3, 'N', NULL, 'ENABLE', NULL, NULL, now(), NULL, 1, NULL);



