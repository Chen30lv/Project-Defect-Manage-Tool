import * as vscode from 'vscode';
import axios, { AxiosInstance } from 'axios';
import { GlobalState } from '../utils/globalState';

async function postData(context: vscode.ExtensionContext): Promise<void> {
	try {
	  const httpClient = axios.create({
      baseURL: 'http://134.175.54.235:8101', // 设置你的baseURL
      withCredentials: true,
    });
  
	  // 使用httpClient进行POST请求
	  const response = await httpClient.post('/api/defectInfo/search/MyDefectInfoVOList?userId=1', {
		// 你的POST数据
	  });
  
	  console.log('Response:', response.data);
      GlobalState.defectInfoArray = response.data.data;
      console.log(GlobalState.defectInfoArray);

	} catch (error) {
	  console.error('Error during data posting:', error);
	}
  }

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
      var childrenList = []
      for (let index = 0; index < GlobalState.defectInfoArray.length; index++) {
        var item = new SideBarEntryItem(
          GlobalState.defectInfoArray[index].defectName,
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
  
  
  vscode.window.registerTreeDataProvider('dashboard', sidebarTodo);
  
  vscode.commands.registerCommand('dashboard.refresh', async () => {
    console.log("Start posting");
    await postData(context);
    sidebarTodo.refresh();
  });

  // let disposable = vscode.commands.registerCommand('dashboard.refresh', async () => {
  //   console.log("Start posting");
  //   sidebarTodo.refresh();
  // });
  // context.subscriptions.push(disposable);
};
