<%-- 
    Document   : sidenav-left
    Created on : 23/09/2017, 21:22:08
    Author     : Lucas
--%>
<%@ taglib prefix = "st" uri = "/WEB-INF/static.tld" %>

<md-sidenav id="sidenav-left" ref="self" class="md-left" :md-swipeable="true">
    <md-toolbar class="md-large">
        <div class="md-toolbar-container">
            <h3 class="md-title">Sidenav content</h3>
        </div>
    </md-toolbar>

    <p>
        Lorem ipsum dolor sit amet, consectetur adipisicing elit.
        Nisi cupiditate esse necessitatibus beatae nobis, 
        deserunt ut est fugit, tempora deleniti, eligendi commodi doloribus. 
        Nemo, assumenda possimus, impedit inventore perferendis iusto!
    </p>
</md-sidenav>

<st:js res="include/sidenav-left/sidenav-left.js"/>