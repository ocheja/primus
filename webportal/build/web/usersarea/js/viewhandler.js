/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
$(document).ready(function() {

    console.log('handling view...');
    var baseView = document.getElementById('baseView').value;
    var parentView = document.getElementById('parentView').value;
    var nodeView = document.getElementById('nodeView').value;

    if (baseView !== null) {
        if (baseView === 'admin') {
            var adminManager = document.getElementById('admin_manager_main');
            var lecturerManager = document.getElementById('lecturer_manager_main');
            var studentManager = document.getElementById('student_manager_main');
            var facultyManager = document.getElementById('faculty_manager_main');
            var departmentManager = document.getElementById('department_manager_main');
            var courseManager = document.getElementById('course_manager_main');
//            var resultManager = document.getElementById('result_manager_main');
            var utilityManager = document.getElementById('utility_manager_main');

            if (parentView !== null) {
                if (parentView === 'admin_mgr') {
                    adminManager.className = 'active submenu open';
                    if (nodeView !== null) {
                        if (nodeView === 'create') {
                            document.getElementById('create_admin_manager').className = 'active';
                        } else if (nodeView === 'view') {
                            document.getElementById('view_admin_manager').className = 'active';
                        } else if (nodeView === 'edit') {
                            document.getElementById('edit_admin_manager').className = 'active';
                        } else if (nodeView === 'delete') {
                            document.getElementById('delete_admin_manager').className = 'active';
                        } else if (nodeView === 'error') {
                        }
                    }
                } else if (parentView === 'lecturer_mgr') {
                    lecturerManager.className = 'active submenu open';

                    if (nodeView !== null) {
                        if (nodeView === 'create') {
                            document.getElementById('create_lecturer_manager').className = 'active';
                        } else if (nodeView === 'view') {
                            document.getElementById('view_lecturer_manager').className = 'active';
                        } else if (nodeView === 'error') {
                        }
                    }
                } else if (parentView === 'student_mgr') {
                    studentManager.className = 'active submenu open';
                    if (nodeView !== null) {
                        if (nodeView === 'create') {
                            document.getElementById('create_student_manager').className = 'active';
                        } else if (nodeView === 'view') {
                            document.getElementById('view_student_manager').className = 'active';
                        } else if (nodeView === 'edit') {
                            document.getElementById('edit_student_manager').className = 'active';
                        } else if (nodeView === 'delete') {
                            document.getElementById('delete_student_manager').className = 'active';
                        } else if (nodeView === 'error') {
                        }
                    }
                } else if (parentView === 'faculty_mgr') {
                    facultyManager.className = 'active submenu open';

                    if (nodeView !== null) {
                        if (nodeView === 'create') {
                            document.getElementById('create_faculty_manager').className = 'active';
                        } else if (nodeView === 'view') {
                            document.getElementById('view_faculty_manager').className = 'active';
                        } else if (nodeView === 'error') {
                        }
                    }
                } else if (parentView === 'department_mgr') {
                    departmentManager.className = 'active submenu open';

                    if (nodeView !== null) {
                        if (nodeView === 'create') {
                            document.getElementById('create_department_manager').className = 'active';
                        } else if (nodeView === 'view') {
                            document.getElementById('view_department_manager').className = 'active';
                        } else if (nodeView === 'error') {
                        }
                    }
                } else if (parentView === 'course_mgr') {
                    courseManager.className = 'active submenu open';

                    if (nodeView !== null) {
                        if (nodeView === 'create') {
                            document.getElementById('create_course_manager').className = 'active';
                        } else if (nodeView === 'view') {
                            document.getElementById('view_course_manager').className = 'active';
                        } else if (nodeView === 'error') {
                        }
                    }
                }
//                } else if (parentView === 'register_mgr') {
//                    resultManager.className = 'active submenu open';
//
//                    if (nodeView !== null) {
//                        if (nodeView === 'create') {
//                            document.getElementById('create_result_manager').className = 'active';
//                        } else if (nodeView === 'view') {
//                            document.getElementById('view_result_manager').className = 'active';
//                        } else if (nodeView === 'edit') {
//                            document.getElementById('edit_result_manager').className = 'active';
//                        } else if (nodeView === 'delete') {
//                            document.getElementById('delete_result_manager').className = 'active';
//                        } else if (nodeView === 'error') {
//                        }
//                    }
//                } 
                else if (parentView === 'utility_mgr') {
                    utilityManager.className = 'active submenu open';

                    if (nodeView !== null) {
                        if (nodeView === 'create') {
                            document.getElementById('create_utility_manager').className = 'active';
                        } else if (nodeView === 'view') {
                            document.getElementById('view_utility_manager').className = 'active';
                        } else if (nodeView === 'error') {
                        }
                    }
                } else {
                    var homeLink = document.getElementById('home_link');
                    if (homeLink !== null) {
                        homeLink.className = 'active';
                    }
                }
            }

        } else if (baseView === 'lecturer') {

        } else if (baseView === 'student') {

        }
    } else {

    }
});

