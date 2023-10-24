import * as vscode from 'vscode';
import {SideBarGeneric, SideBarEntryItem} from './dashboard';

const dashboard = require('./dashboard');

export const defects = [
    {
      defect: 'defect 1',
    },
    {
      defect: 'defect 2',
    },
    {
      defect: 'defect 3',
    },
    {
      defect: 'defect 4',
    },
    {
      defect: 'defect 5',
    }
  ];

export class FindList{
    private defectTexts: string[];
    private selectedDefects: string[] = [];
    private sidebarTodo: SideBarGeneric;

    constructor() {
        this.defectTexts = defects.map(defect => defect.defect);
        this.sidebarTodo = new SideBarGeneric('dashboard.openChild');
    }
    list = vscode.commands.registerCommand('dashboard.list',  () => {
        vscode.window.showQuickPick(
            this.defectTexts,
            {
                canPickMany:true,
                ignoreFocusOut:true,
                matchOnDescription:true,
                matchOnDetail:true,
                placeHolder:'请选择需要访问的缺陷'
            })
            .then((selectedDefects) =>{
                this.showSelectedDefects(selectedDefects);
        });

    });
    getSelectedDefects() {
        return this.selectedDefects;
    }
    getSidebarTodo() {
        return this.sidebarTodo;
    }
    private showSelectedDefects(selectedDefects: string[] | undefined) {
        if (selectedDefects) {
          const message = `你选择的缺陷是: ${selectedDefects.join(', ')}`;
          vscode.window.showInformationMessage(message);
        }
    }
}

module.exports = function (context: vscode.ExtensionContext) {
    const findlist = new FindList();

    vscode.commands.registerCommand('dashboard.list', () => {
        findlist.list;
    });

};