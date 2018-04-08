import React, { Component } from 'react';
import { Button, Form, Grid, Header, Image, Message, Segment } from 'semantic-ui-react'
var firebase = require('firebase');

  var config = {
    apiKey: "AIzaSyA1wyUZz9KID97qDriI6PvDlu_2bjJNyUY",
    authDomain: "nyc-tlc-app.firebaseapp.com",
    databaseURL: "https://nyc-tlc-app.firebaseio.com",
    projectId: "nyc-tlc-app",
    storageBucket: "nyc-tlc-app.appspot.com",
    messagingSenderId: "463771832968"
  };
  firebase.initializeApp(config);


class Authen extends Component {

  login(event){
    const email = this.refs.email.value;
    const password = this.refs.password.value;
    console.log(email, password);

    const auth = firebase.auth();

    const promise = auth.signInWithEmailAndPassword(email, password);

    promise.then(user => {
      var lout = document.getElementById('logout');

      //Write a welcome message for user
       var err = "welcome " +user.email
       firebase.database().ref('users/'+user.uid).set({
        email: user.email
      });
      console.log(user);
      this.setState({err: err});
      
      lout.classList.remove('hide');
    });

    promise.catch(e => {
      var err = e.message;
      console.log(err);
      this.setState({err: err});
    });
  }

  signup(){
    const email = this.refs.email.value;
    const password = this.refs.password.value;
    console.log(email, password);

    const auth = firebase.auth();

    const promise = auth.createUserWithEmailAndPassword(email, password);

    promise
    .then(user => {
      var err = "Welcome "+ user.email;
      firebase.database().ref('users/'+user.uid).set({
        email: user.email
      });
      console.log(user);
      this.setState({err: err});
    });
    promise
    .catch(e => {
      var err = e.message;
      console.log(err);
      this.setState(({err: err}));
    });
  }

  logout(){
    firebase.auth().signOut();
    var lout = document.getElementById('logout');

    //Write a thanks message for user
    lout.classList.add('hide');
  }

  google(){
    console.log("I am in google method");

    var provider = new firebase.auth.GoogleAuthProvider();
    var promise = firebase.auth().signInWithPopup(provider);

    promise.then( result => {
      var user = result.user;
      console.log(result);
      firebase.database().ref('users/'+user.uid).set({
        email: user.email,
        name: user.displayName
      });

    });
    promise.catch(e => {
      var msg = e.message;
      console.log(msg);
    });

  }

  constructor(props){
    super(props);

    this.state = {
      err: ''
    };

    this.login = this.login.bind(this);
    this.signup = this.signup.bind(this);
    this.logout = this.logout.bind(this);
    this.google = this.google.bind(this);
  }

  render(){
    return(
     <div className='login-form'>
           <style>{`
               body > div,
               body > div > div,
              body > div > div > div.login-form {
              height: 100%;
             }
            `}</style>
        <Grid
          textAlign='center'
          style={{ height: '100%' }}
          verticalAlign='middle'
        >
      <Grid.Column style={{ maxWidth: 450 }}>
        <Header as='h2' color='teal' textAlign='center'>
         Log-in to your account
        </Header>
        <Form size='large'>
          <Segment stacked>
            {/* <Form.Input
              fluid
              icon='user'
              iconPosition='left'
              id="email" ref="email" type="email" placeholder="Enter your email"
            //   placeholder='E-mail address'
            /> */}
             <input fluid id="email" icon='user'
              iconPosition='left' ref="email" type="email" placeholder="Enter your email" />
            {/* <Form.Input
              fluid
              icon='lock'
              iconPosition='left'
            //   placeholder='Password'
            //   type='password'
            id="pass" ref="password" type="password" placeholder="Enter your password"
            /> */}
            <input id="pass" ref="password" type="password" placeholder="Enter your password" />
            <p>{this.state.err}</p>

            <Button onClick={this.login} color='teal' fluid size='large'>Login</Button>
          </Segment>
          
        </Form>
        <Message>
          New to us? <button onClick={this.signup}>Sign Up </button>
          <button onClick={this.logout} id="logout" className="hide">Log out</button>
        </Message>
     </Grid.Column>
    </Grid>
     
        {/* <input id="email" ref="email" type="email" placeholder="Enter your email" /><br /> */}
        {/* <input id="pass" ref="password" type="password" placeholder="Enter your password" /><br /> */}
        {/* <p>{this.state.err}</p> */}
        {/* <button onClick={this.login}>Log In</button> */}
        {/* <button onClick={this.signup}>Sign Up</button> */}
        {/* <button onClick={this.logout} id="logout" className="hide">Log out</button><br /> */}
        {/* <button onClick={this.google} id="google" className="google">Sign In with Google</button> */}
    </div>
      
    )
  }
}


export default Authen;



