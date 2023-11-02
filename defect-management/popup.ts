import * as vscode from 'vscode';
import * as moment from 'moment-timezone';

// set time
const showPopupTime = ["17:06", "20:00"];
const message = 'Reminder of Defect-Management-System';
let content = ' ';
let doneDefects = 1;
let allDefects = 100; 
let donePercent = 100 * doneDefects / allDefects;


module.exports = function (context: vscode.ExtensionContext) {
    const timer = setInterval(() => {
        const now = moment().tz("Asia/Hongkong");
        const targetTime1 = moment(showPopupTime[0], "HH:mm");
        const targetTime2 = moment(showPopupTime[1], "HH:mm");
        //获取真正的doneDefects和allDefects
        donePercent = 100 * doneDefects / allDefects;
      
        // check time every 30 seconds
        if (now.hour() === targetTime1.hour() && now.minute() === targetTime1.minute()) {
          content = 'Good Afternoon! You have fixed ' + donePercent + '% of defects! Remember to have a rest.';
          vscode.window.showInformationMessage(message, content);
        }
      
        if (now.hour() === targetTime2.hour() && now.minute() === targetTime2.minute()) {
          content = 'Good Evening! You have fixed ' + donePercent + '% of defects! It\'s time to finish the work today.';
          vscode.window.showInformationMessage(message, content);
        }
      }, 3000);

};