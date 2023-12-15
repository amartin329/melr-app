<template>
  <div id="capstone-app" class="container">
    <header>
      <img src="./assets/melr.png">
    </header>
    
    <nav id="nav" class="d-flex align-items-start">
      <div id="nav-list" class="nav nav-underline flex-column" aria-orientation="vertical">
        <router-link class="nav-link" v-bind:to="{ name: 'home' }">Home</router-link>
        <router-link class="nav-link" v-bind:to="{name: 'recipes'}">My Recipes</router-link>
        <router-link class="nav-link" v-bind:to="{name: 'meals'}">My Meals</router-link>
        <router-link class="nav-link" v-bind:to="{name: 'plans'}">My Meal Plans</router-link>
        <router-link class="nav-link" v-bind:to="{name: 'grocery-list'}">My Grocery List</router-link>
        <router-link class="nav-link" v-bind:to="{ name: 'logout' }" v-if="this.$store.state.token != ''">Logout</router-link>
      </div>
    </nav>
    <main>
      <div class="content">
        <router-view />
      </div>
    </main>
    <aside>
      <div class="content">
      </div>
    </aside>
    <footer>
      <div class="content">
        <p>Application by Alex Martin, Hannah Pham, and Greg Van Giezen</p>
      </div>
    </footer>
   
  </div>
</template>


<style>
@import 'bootstrap/dist/css/bootstrap.min.css';
@import url("https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.2/font/bootstrap-icons.min.css");
    body {
        margin: 0;
    }
    *{
      /* font-family: 'Trebuchet MS', sans-serif; */
    }

    /* Add CSS Grid here  background colors: Header - #f69a00;  Nav - #f2ead2;  
    main - #f9f5f0;  aside - #f2ead2;  footer - #331312;
    font colors header, footer - #ffffff;  nav, main, aside - #74889a;*/

    .container{
        display: grid;

        grid-template-areas: 
            "header header header" 
            "nav content side"
            "footer footer footer";

        grid-template-columns: 200px 1fr 200px;
        grid-template-rows: 125px 1fr 85px;
        gap: 10px;

        height: 100vh;


    }
    /*handle responsive behavior */
    @media (max-width: 768px) {
        .container{
            grid-template-areas: 
                "header"
                "nav"
                "content"
                "side"
                "footer";
            grid-template-columns: 1fr;
            grid-template-rows:
                auto /*Header */
                75px /*Nav*/
                1fr /* content */
                75px /* Sidebar */
                auto; /* Footer */
            
        }
        header, footer, nav, main, aside {
            padding: 5px 0;
        }
        nav, aside {
        margin: 0;
        }

    }

    header {
    grid-area: header;
    background-color: #ffffff;
    text-align: center;
    border-bottom: 2px solid black;
    }

    
    header > img {

      height: 100%;
    }

   #nav {
        grid-area: nav;        
        background-color:  #95E0B7;
        display:flex;
        height: 100%;
        
    }


    #nav-list{
      display:flex;
      flex-direction: column;
      justify-content: space-evenly;
      align-content: space-around;
     
    }
    #nav > a{
      padding: 10px;
    }

    router-link {
      /* text-decoration: none; */
    }


    main {
        grid-area: content;
        background-color: white;
        /* margin-top: 50px; */
        /* background-image: url("https://lh4.googleusercontent.com/Vrf5VUD-g3nJgSJc29YXKEJ7UomJhNi3qkXo6Cie6m-9Mq1GsluJJ6LC9_R2WCqd2_yAXN82MS9xhaTBV5n2OEQu485rPwFOiHdkh4F2ETeINj0Vwrl8e6loHOBy15d1n9L3CZ_8");
        background-repeat:repeat-y;
        background-size: 100%; */
    }

    aside {
        grid-area: side;        
        background-color:  #95E0B7;
    }

    footer {
        grid-area: footer;
        background-color:  #95E0B7;
    }

    header, footer {
        /* color: #ffffff; */
    }
    nav, main, aside {
        /* color:#00ff75; */
    }

    h1, h2 {
  -webkit-text-stroke-width: 2.4px;
  -webkit-text-stroke-color: black;
    }

  h3, h4 {
    -webkit-text-stroke-width: 1.3px;
  -webkit-text-stroke-color: black;
  
}
    header, footer, nav, main, aside {
        font-family:'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
        font-size: 24px;
        text-transform: uppercase;
    }

    div.content {
        text-align: center;
        padding-top: 20px;
        /* display:flex;
        flex-direction: row;
        align-items: center; */
    }

    .nav-link{
      /* background-color: white; */
      color: rgb(60, 60, 60);
      text-align: center;
      margin-top: 15px;
      width:100%;
      /* border: 3px solid black; */
    }
    /* #nav-list{
      display: flex;
      flex-direction: column;
    } */
</style>