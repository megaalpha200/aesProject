import React, { Component } from 'react';
import { Button, Form, Tab, Tabs } from 'react-bootstrap';
import logo from './logo.svg';
import './App.css';
import 'bootstrap/dist/css/bootstrap.css';
import * as conversions from './assets/conversions';
import  * as aux from './assets/auxFuncs';
import * as aesFuncs from './assets/aesMainFunctions';

class ControlledTabs extends React.Component {
  constructor(props, context) {
    super(props, context);
  }

  render() {
    return (
      <Tabs
        id="controlled-tabs"
        activeKey={this.props.currSelectedTabKey}
        onSelect={(key)=> this.props.onSelect(key)}
      >
        <Tab eventKey="string" title="Input as String">
        </Tab>
        <Tab eventKey="hex" title="Input as Hexadecimal">
        </Tab>
      </Tabs>
    );
  }
}

class App extends Component {
  constructor(props) {
    super(props);
    this.state = {
      currSelectedTabKey: "string",
      keyCharLimit: "16",
      plaintext: "",
      key: "",
      plaintextPlaceholder: "Enter Plaintext as a string...",
      keyPlaceholder: "Enter 128-bit Key as a string...",
      cipherTextHex: "",
      cipherTextStr: "",
      isSubmitted: false,
      console: {
        currConsoleLineNum: -1,
        consoleLines: [{
          lineNum: 0,
          output: "Welcome!",
        }],
      },
      errMsg: "",
    };

    this.handleChange = this.handleChange.bind(this);
    this.handleSubmit = this.handleSubmit.bind(this);
  }

  handleChange(event) {
    this.setState({
      [event.target.id]: event.target.value,
      cipherTextHex: "",
      cipherTextStr: "",
      isSubmitted: false,
      console: {
        currConsoleLineNum: -1,
        consoleLines: [],
      },
      errMsg: "",
    });
  }

  handleTabSelected(key) {
    let plaintextPlaceholder;
    let keyPlaceholder;
    let keyCharLimit;

    console.log(key);

    if (key === "string") {
      plaintextPlaceholder = "Enter Plaintext as a string...";
      keyPlaceholder = "Enter 128-bit Key as a string...";
      keyCharLimit = "16";
    }
    else if (key === "hex") {
      plaintextPlaceholder = "Enter Plaintext as a hexadecimal...";
      keyPlaceholder = "Enter 128-bit Key as a hexadecimal...";
      keyCharLimit = "32";
    }

    document.getElementById("plaintext").value = "";
    document.getElementById("key").value = "";

    this.setState({ 
      currSelectedTabKey: key,
      plaintextPlaceholder: plaintextPlaceholder,
      keyPlaceholder: keyPlaceholder,
      keyCharLimit: keyCharLimit,
      plaintext: "",
      key: "",
      cipherTextHex: "",
      cipherTextStr: "",
      isSubmitted: false,
      console: {
        currConsoleLineNum: -1,
        consoleLines: [],
      },
    });
  }

  handleSubmit(event) {
    event.preventDefault();

    // try {
      const plaintextBin = (this.state.currSelectedTabKey === "string") ? 
        conversions.convertStringToHex(this.state.plaintext) : this.state.plaintext;
      const keyBin = (this.state.currSelectedTabKey === "string") ? 
          conversions.convertStringToHex(this.state.key) : this.state.key;
      const cipherTextHex = aesFuncs.aesEncrypt(plaintextBin, keyBin);
      const cipherTextStr = conversions.convertHexToString(cipherTextHex);
      let outputLines = aesFuncs.outputLines;

      outputLines.push({output: `Ciphertext (hex): ${aux.string_chop(cipherTextHex, 2).join(" ")}`});
      outputLines.push({output: `Ciphertext (string): ${cipherTextStr}`});
      outputLines = this.enumerateConsoleLines(outputLines);

      this.setState({
        cipherTextHex: cipherTextHex,
        cipherTextStr: cipherTextStr,
        isSubmitted: true,
        console: {
          currConsoleLineNum: outputLines.length - 1,
          consoleLines: outputLines,
        },
      });
    /* }
    catch(e) {
      this.setState({
        errMsg: `An error has occured: ${e.toString()}`,
      });
    } */
  }

  renderConsoleLine(lineNum, output) {
    return(
      <div className="output-line" key={lineNum}>
        <span className="output-line-num">{lineNum.toString().padStart(this.state.console.currConsoleLineNum.toString().length, '0')}</span>
        <div className="output-line-text"><em>{(output === "") ? "\r\n" : output}</em></div>
      </div>
    );
  }

  renderConsoleLines(lines) {
    const renderedLines = [];

    lines.forEach((line) =>{
      renderedLines.push(this.renderConsoleLine(line.lineNum, line.output));
    });

    return renderedLines;
  }

  addConsoleLine(lineNum, output) {
    const consoleLines = this.state.console.consoleLines.slice(0, this.state.console.currConsoleLineNum + 1);

    this.setState({
      console: {
        currConsoleLineNum: -1,
        consoleLines: consoleLines.concat(
          {
            lineNum: lineNum,
            output: output,
          }
        ),
      }
    });
  }

  enumerateConsoleLines(lines) {
    const enumLines = [];
    let currLineNum = -1;

    lines.forEach((line) => {
      line.lineNum = ++currLineNum;
      enumLines.push(line);
    });

    return enumLines;
  }

  render() {
    let resHex = "";
    let resStr = "";
    let resErr = this.state.errMsg;

    if (this.state.isSubmitted) {
      // try {
        resHex = aux.string_chop(this.state.cipherTextHex, 2).join(" ");
        resStr = this.state.cipherTextStr;
      /* }
      catch(e) {
        resErr = `An error has occured: ${e.toString()}`;
      } */
    }
    else {
      resHex = "";
      resStr = "";
    }

    return (
      <div>
        <div className="App">
          <header className="App-header">
              <h1>AES Encryptor</h1>
              <br/>
              <ControlledTabs
                currSelectedTabKey={this.state.currSelectedTabKey}
                onSelect={(key) => this.handleTabSelected(key)}
               />
              <br/>
              <Form onSubmit={this.handleSubmit}>
                <Form.Control id="plaintext" size="lg" type="text"  autoComplete="off" onChange={(e) => this.handleChange(e)} placeholder={this.state.plaintextPlaceholder} />
                <br/>
                <Form.Control id="key" size="lg" type="text" autoComplete="off" maxLength={this.state.keyCharLimit} onChange={(e) => this.handleChange(e)} placeholder={this.state.keyPlaceholder} />
                <br/>
                <Button variant="primary" type="submit" block>Encrypt</Button>
              </Form>
              <br/>
              <h4 hidden={resErr === ""} style={{color: "red"}}>
                {resErr}
              </h4>
              <h4 hidden={!this.state.isSubmitted}>
                <span style={{textDecoration: "underline"}}>Ciphertext (hex):</span> {resHex}
              </h4>
              <h4 hidden={!this.state.isSubmitted}>
                <span style={{textDecoration: "underline"}}>Ciphertext (string):</span> {resStr}
              </h4>
              <br/>
              <div hidden={!this.state.isSubmitted}>
                <h5 style={{textAlign: "left", textDecoration: "underline"}}><em>Console Output:</em></h5>
                <br />
                <div className="Console-output">
                  {(this.state.console.consoleLines.length !== 0) ? this.renderConsoleLines(this.state.console.consoleLines) : ""}
                </div>
              </div>
              <br/>
              <h6>Created by <a href="https://github.com/megaalpha200" target="new_tab">Jose A. Alvarado</a> for CS 547 - Texas Southern University</h6>
              <h6>The GitHub repository can be found <a href="https://github.com/megaalpha200/aesProject" target="new_tab">here</a>.</h6>
              <h6>&copy; <a href="http://jaaproductions.tk" target="new_tab">J.A.A. Productions</a> 2019</h6>
          </header>
        </div>
      </div>
    );
  }
}

export default App;
