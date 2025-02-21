# MVVM Architecture

## TODO
- Complete all test cases functions
- Use API for Features
- Fix Search Repository when selecting the second user

## Technology
- Android room local database
- Android MVVM Architecture
- Android State Flow
- Android ViewModel


# Features
## Github Users List by given name
- Show Users from Local
- TODO load user from github api
  <img width="200" alt="Screenshot 2024-03-09 at 1 41 14" src="https://github.com/dinkar1708/AndroidMVVMCleanArchitectureTemplate/assets/14831652/b528827b-7729-4d5c-ae89-a1f004ff01cf">

## Show repositories list for selected user
- Show Repositories of Selected User from Local
- TODO load repository from github api
  <img width="200" alt="Screenshot 2024-03-09 at 1 41 01" src="https://github.com/dinkar1708/AndroidMVVMCleanArchitectureTemplate/assets/14831652/7364bf51-5f6e-4b95-b53e-5133233d6ca5">

## Mock Data for Testing
- Documentation https://mockk.io/

## How to Use Resources
### How to Use Color
- Defined in `com.example.clean.ui.theme.Theme.kt` file.
- Use as: `style = MaterialTheme.colorScheme.background`

### How to Use Font Size
- Defined in `com.example.clean.ui.theme.Typography.kt` file.
- Use as: `style = MaterialTheme.typography.bodyLarge`

## How to Create Test Cases

### Repository Implementation Test Cases

1. **Click on the Class for Which You Want to Create a Test Case:**
- For example, to create a test case for the `SearchRepositoryImpl` class, hover your mouse over it and click to create.

![Step 1](https://github.com/dinkar1708/AndroidMVVMCleanArchitectureTemplate/assets/14831652/d5d48945-08ff-4b89-a500-747b1e937dc1)

2. **Select the Checkbox for Desired Functions:**
- After clicking to create a test case, select the checkboxes corresponding to the functions you want to include in the test case.

![Step 2](https://github.com/dinkar1708/AndroidMVVMCleanArchitectureTemplate/assets/14831652/722a9d19-775f-4750-ac3e-234a3ef4fe44)

4. **Generate Test Inside the Test Folder:**
- Once you've selected the functions, the system will generate the test inside the test folder.

![Step 3](https://github.com/dinkar1708/AndroidMVVMCleanArchitectureTemplate/assets/14831652/feba483c-396f-44c0-8fb4-7e2689060840)

Follow these steps to efficiently create test cases for your classes.
### all test cases screen shot
<img width="1238" alt="Screenshot 2024-03-09 at 1 36 56" src="https://github.com/dinkar1708/AndroidMVVMCleanArchitectureTemplate/assets/14831652/111db719-d401-4ac1-b4d7-0cdaf8f03d50">

# APIs
## List Users by User Name
3. **Search:**
- Search Repositories: `https://api.github.com/search/repositories?q=:query`
- Search Users: `https://api.github.com/search/users?q=:query`
  (https://api.github.com/search/users?q=dinkar1708)

{
"total_count": 2,
"incomplete_results": false,
"items": [
{
"login": "dinkar1708",
"id": 14831652,
"node_id": "MDQ6VXNlcjE0ODMxNjUy",
"avatar_url": "https://avatars.githubusercontent.com/u/14831652?v=4",
"gravatar_id": "",
"url": "https://api.github.com/users/dinkar1708",
"html_url": "https://github.com/dinkar1708",
"followers_url": "https://api.github.com/users/dinkar1708/followers",
"following_url": "https://api.github.com/users/dinkar1708/following{/other_user}",
"gists_url": "https://api.github.com/users/dinkar1708/gists{/gist_id}",
"starred_url": "https://api.github.com/users/dinkar1708/starred{/owner}{/repo}",
"subscriptions_url": "https://api.github.com/users/dinkar1708/subscriptions",
"organizations_url": "https://api.github.com/users/dinkar1708/orgs",
"repos_url": "https://api.github.com/users/dinkar1708/repos",
"events_url": "https://api.github.com/users/dinkar1708/events{/privacy}",
"received_events_url": "https://api.github.com/users/dinkar1708/received_events",
"type": "User",
"site_admin": false,
"score": 1.0
},
]
}

### Repositories by User Name
1. **User:**
- URL: `https://api.github.com/users/octocat`
- Replace `octocat` with the desired GitHub username.
1. **Users:**
- User Details: `https://api.github.com/users/:username`
- User Repositories: `https://api.github.com/users/:username/repos`

https://api.github.com/users/dinkar1708/repos

- Endpoint: `users/dinkar1708/repos?per_page=3`ching user details and repositories.

[
{
"id": 210987116,
"node_id": "MDEwOlJlcG9zaXRvcnkyMTA5ODcxMTY=",
"name": "android-architecture",
"full_name": "dinkar1708/android-architecture",
"private": false,
"owner": {
"login": "dinkar1708",
"id": 14831652,
"node_id": "MDQ6VXNlcjE0ODMxNjUy",
"avatar_url": "https://avatars.githubusercontent.com/u/14831652?v=4",
"gravatar_id": "",
"url": "https://api.github.com/users/dinkar1708",
"html_url": "https://github.com/dinkar1708",
"followers_url": "https://api.github.com/users/dinkar1708/followers",
"following_url": "https://api.github.com/users/dinkar1708/following{/other_user}",
"gists_url": "https://api.github.com/users/dinkar1708/gists{/gist_id}",
"starred_url": "https://api.github.com/users/dinkar1708/starred{/owner}{/repo}",
"subscriptions_url": "https://api.github.com/users/dinkar1708/subscriptions",
"organizations_url": "https://api.github.com/users/dinkar1708/orgs",
"repos_url": "https://api.github.com/users/dinkar1708/repos",
"events_url": "https://api.github.com/users/dinkar1708/events{/privacy}",
"received_events_url": "https://api.github.com/users/dinkar1708/received_events",
"type": "User",
"site_admin": false
},
"html_url": "https://github.com/dinkar1708/android-architecture",
"description": "A collection of samples to discuss and showcase different architectural tools and patterns for Android apps.",
"fork": true,
"url": "https://api.github.com/repos/dinkar1708/android-architecture",
"forks_url": "https://api.github.com/repos/dinkar1708/android-architecture/forks",
"keys_url": "https://api.github.com/repos/dinkar1708/android-architecture/keys{/key_id}",
"collaborators_url": "https://api.github.com/repos/dinkar1708/android-architecture/collaborators{/collaborator}",
"teams_url": "https://api.github.com/repos/dinkar1708/android-architecture/teams",
"hooks_url": "https://api.github.com/repos/dinkar1708/android-architecture/hooks",
"issue_events_url": "https://api.github.com/repos/dinkar1708/android-architecture/issues/events{/number}",
"events_url": "https://api.github.com/repos/dinkar1708/android-architecture/events",
"assignees_url": "https://api.github.com/repos/dinkar1708/android-architecture/assignees{/user}",
"branches_url": "https://api.github.com/repos/dinkar1708/android-architecture/branches{/branch}",
"tags_url": "https://api.github.com/repos/dinkar1708/android-architecture/tags",
"blobs_url": "https://api.github.com/repos/dinkar1708/android-architecture/git/blobs{/sha}",
"git_tags_url": "https://api.github.com/repos/dinkar1708/android-architecture/git/tags{/sha}",
"git_refs_url": "https://api.github.com/repos/dinkar1708/android-architecture/git/refs{/sha}",
"trees_url": "https://api.github.com/repos/dinkar1708/android-architecture/git/trees{/sha}",
"statuses_url": "https://api.github.com/repos/dinkar1708/android-architecture/statuses/{sha}",
"languages_url": "https://api.github.com/repos/dinkar1708/android-architecture/languages",
"stargazers_url": "https://api.github.com/repos/dinkar1708/android-architecture/stargazers",
"contributors_url": "https://api.github.com/repos/dinkar1708/android-architecture/contributors",
"subscribers_url": "https://api.github.com/repos/dinkar1708/android-architecture/subscribers",
"subscription_url": "https://api.github.com/repos/dinkar1708/android-architecture/subscription",
"commits_url": "https://api.github.com/repos/dinkar1708/android-architecture/commits{/sha}",
"git_commits_url": "https://api.github.com/repos/dinkar1708/android-architecture/git/commits{/sha}",
"comments_url": "https://api.github.com/repos/dinkar1708/android-architecture/comments{/number}",
"issue_comment_url": "https://api.github.com/repos/dinkar1708/android-architecture/issues/comments{/number}",
"contents_url": "https://api.github.com/repos/dinkar1708/android-architecture/contents/{+path}",
"compare_url": "https://api.github.com/repos/dinkar1708/android-architecture/compare/{base}...{head}",
"merges_url": "https://api.github.com/repos/dinkar1708/android-architecture/merges",
"archive_url": "https://api.github.com/repos/dinkar1708/android-architecture/{archive_format}{/ref}",
"downloads_url": "https://api.github.com/repos/dinkar1708/android-architecture/downloads",
"issues_url": "https://api.github.com/repos/dinkar1708/android-architecture/issues{/number}",
"pulls_url": "https://api.github.com/repos/dinkar1708/android-architecture/pulls{/number}",
"milestones_url": "https://api.github.com/repos/dinkar1708/android-architecture/milestones{/number}",
"notifications_url": "https://api.github.com/repos/dinkar1708/android-architecture/notifications{?since,all,participating}",
"labels_url": "https://api.github.com/repos/dinkar1708/android-architecture/labels{/name}",
"releases_url": "https://api.github.com/repos/dinkar1708/android-architecture/releases{/id}",
"deployments_url": "https://api.github.com/repos/dinkar1708/android-architecture/deployments",
"created_at": "2019-09-26T02:59:47Z",
"updated_at": "2019-09-26T21:30:35Z",
"pushed_at": "2019-09-19T11:14:11Z",
"git_url": "git://github.com/dinkar1708/android-architecture.git",
"ssh_url": "git@github.com:dinkar1708/android-architecture.git",
"clone_url": "https://github.com/dinkar1708/android-architecture.git",
"svn_url": "https://github.com/dinkar1708/android-architecture",
"homepage": "",
"size": 12072,
"stargazers_count": 0,
"watchers_count": 0,
"language": null,
"has_issues": false,
"has_projects": true,
"has_downloads": true,
"has_wiki": true,
"has_pages": false,
"has_discussions": false,
"forks_count": 0,
"mirror_url": null,
"archived": false,
"disabled": false,
"open_issues_count": 0,
"license": {
"key": "apache-2.0",
"name": "Apache License 2.0",
"spdx_id": "Apache-2.0",
"url": "https://api.github.com/licenses/apache-2.0",
"node_id": "MDc6TGljZW5zZTI="
},
"allow_forking": true,
"is_template": false,
"web_commit_signoff_required": false,
"topics": [

    ],
    "visibility": "public",
    "forks": 0,
    "open_issues": 0,
    "watchers": 0,
    "default_branch": "master"
}
]