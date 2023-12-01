import * as vscode from 'vscode';
import {SideBarGeneric} from './dashboard';


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
    

    constructor() {
        this.defectTexts = defects.map(defect => defect.defect);
        
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
    
    private showSelectedDefects(selectedDefects: string[] | undefined) {
        if (selectedDefects) {
          const message = `你选择的缺陷是: ${selectedDefects.join(', ')}`;
          vscode.window.showInformationMessage(message);
        }
    }
}

module.exports = function (context: vscode.ExtensionContext) {
    const findlist = new FindList();

};