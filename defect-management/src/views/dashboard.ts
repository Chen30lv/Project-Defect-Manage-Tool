import * as vscode from 'vscode';
import { GlobalState } from '../utils/globalState';

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

const defect2 = GlobalState.defectInfoArray;
console.log("Defects:")
console.log(GlobalState.defectInfoArray)
console.log(defect2)

// Custom Tree Item Class
export class SideBarEntryItem extends vscode.TreeItem {
  constructor(public readonly label: string, public readonly collapsibleState: vscode.TreeItemCollapsibleState
  ) {
    super(label, collapsibleState);
    this.tooltip = `${this.label}`;
    if (this.label != "Todo" && this.label != "Finished" && this.label != "Statistics") {
      this.description = `Project ${Math.ceil(Math.random() * 1000)}`;
    }
  }
}

export class SideBarGeneric implements vscode.TreeDataProvider<SideBarEntryItem> {
  private _onDidChangeTreeData: vscode.EventEmitter<SideBarEntryItem | undefined | null | void> = new vscode.EventEmitter<SideBarEntryItem | undefined | null | void>();
  readonly onDidChangeTreeData: vscode.Event<SideBarEntryItem | undefined | null | void> = this._onDidChangeTreeData.event;
  
  constructor(private workspaceRoot?: string) {}

  refresh(): void {
    this._onDidChangeTreeData.fire();
  }

  getTreeItem(element: SideBarEntryItem): vscode.TreeItem {
    return element;
  }

  getChildren(element?: SideBarEntryItem): vscode.ProviderResult<SideBarEntryItem[]> {
    if (element) {
      // Child nodes
      console.log(element.label);
      var childrenList = []
      for (let index = 0; index < defects.length; index++) {
        var item = new SideBarEntryItem(
          defects[index].defect,
          vscode.TreeItemCollapsibleState.None
        )
        item.command = {
          command: 'dashboard.openChild', 
          title: defects[index].defect,
          arguments: [defects[index].defect], 
        }
        childrenList[index] = item
      }
      return childrenList
    } else {
      // Create root tags
      return [
        new SideBarEntryItem(
          'Todo',
          vscode.TreeItemCollapsibleState.Expanded
        ),
        new SideBarEntryItem(
          'Finished',
          vscode.TreeItemCollapsibleState.Expanded
        ),
        new SideBarEntryItem(
          'Statistics',
          vscode.TreeItemCollapsibleState.Expanded
        ),
      ]
    }
  }
}

module.exports = function (context: vscode.ExtensionContext) {
  // Register Sidebar Panels
  const sidebarTodo = new SideBarGeneric('dashboard.openChild');
  context.globalState.update('sidebarTodo', sidebarTodo);
  
  vscode.window.registerTreeDataProvider('dashboard', sidebarTodo);
  
  vscode.commands.registerCommand('dashboard.refresh', () => {
    sidebarTodo.refresh();
  });
};
