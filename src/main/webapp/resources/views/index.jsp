<%-- 
    Document   : index
    Created on : 23/08/2017, 22:27:17
    Author     : Lucas
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Loundry On-Line</title>
        <link rel="stylesheet" href="./node_modules/vue-material/dist/vue-material.css"/>
    </head>

    <body>
        <div id="app">
            <md-toolbar>
                <md-button class="md-icon-button" @click="toggleSidenav">
                    <md-icon>menu</md-icon>
                </md-button>
                <h1 class="md-title">{{msg}}</h1>
            </md-toolbar>
            <md-sidenav class="md-left" ref="sidenav" @open="open('Left')" @close="close('Left')">
                <md-toolbar class="md-large">
                    <div class="md-toolbar-container">
                        <h3 class="md-title">Sidenav content</h3>
                    </div>
                </md-toolbar>

                <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Nisi cupiditate esse necessitatibus beatae nobis, deserunt ut est fugit, tempora deleniti, eligendi commodi doloribus. Nemo, assumenda possimus, impedit inventore perferendis iusto!</p>
            </md-sidenav>
            <div class="main-content">
                <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Quo, rerum? Error sunt, aperiam dolores, atque expedita molestiae tenetur. Quis eveniet accusamus velit explicabo adipisci reiciendis modi eaque quas, officia excepturi.</p>
            </div>
        </div>
        <script type="text/javascript" src="./node_modules/vue/dist/vue.js"></script>
        <script type="text/javascript" src="./node_modules/vue-material/dist/vue-material.js"></script>
        <script type="text/javascript" src="./index.js"></script>
    </body>

</html>