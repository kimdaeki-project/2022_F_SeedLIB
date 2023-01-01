<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@  taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
      <header class="header">
        <div class="container-fluid">
          <div class="row">
            <div class="col-lg-5 col-md-5 col-6">
              <div class="header-left d-flex align-items-center">
                <div class="menu-toggle-btn mr-20">
                  <button
                    id="menu-toggle"
                    class="main-btn info-btn btn-hover"
                  >
                    <i class="lni lni-chevron-left me-2"></i>
                    <b>메뉴</b>
                  </button>
                </div>
              </div>
            </div>
            <div class="col-lg-7 col-md-7 col-6">
              <div class="header-right">
                <!-- 로그인 했을때 아이콘 -->
                <sec:authorize access="isAuthenticated()">
                  <!-- profile start -->
                  <div class="profile-box ml-15">
                    <div class="profile-info">
                      <div class="info">
                        <button
                          class="dropdown-toggle bg-transparent border-0"
                          type="button"
                          id="profile"
                          data-bs-toggle="dropdown"
                          aria-expanded="false"
                          >
                          <h6><sec:authentication property="Principal.name"/></h6>
                        </button>
                        <ul
                          class="dropdown-menu dropdown-menu-end"
                          aria-labelledby="profile"
                        >
                          <li>
                            <a href="../member/logoutResult"> <i class="lni lni-exit"></i>로그아웃</a>
                          </li>
                        </ul>
                        <div class="image">
                          <img
                            src="/images/logo200.png" 
                            alt="씨앗도서관"
                          />
                          <span class="status"></span>
                        </div>
                      </div>
                    </div>
                  </div>
                  <!-- profile end -->
				        </sec:authorize >
              </div>
            </div>
          </div>
        </div>
      </header>