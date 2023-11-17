import * as vscode from 'vscode';
import axios, { AxiosInstance } from 'axios';
import { GlobalState } from '../utils/globalState';

function selectEmoji(Level: string): string {
  switch (Level) {
    case "Critical":
      return "🔴"; 
    case "High":
      return "🟠"; 
    case "Medium":
      return "🟡"; 
    case "Low":
      return "⚪"; 
  }
  return "⚪"; 
}

async function postData(context: vscode.ExtensionContext): Promise<void> {
	try {
	  const httpClient = axios.create({
      baseURL: 'http://134.175.54.235:8101', // 设置你的baseURL
      withCredentials: true,
    });
  
	  // 使用httpClient进行POST请求
	  const response = await httpClient.post('/api/defectInfo/search/MyDefectInfoVOList?userId=2', {
		// 你的POST数据
	  });
  
	  console.log('Response:', response.data);
      GlobalState.defectInfoArray = response.data.data;
      console.log(GlobalState.defectInfoArray);

	} catch (error) {
	  console.error('Error during data posting:', error);
	}
}


// Custom Tree Item Class
export class SideBarEntryItem extends vscode.TreeItem {
  constructor(public readonly defectName: string, public readonly defectStatus: string, public readonly defectType: string, public readonly defectLevel: string, public readonly defectDetail: string, public readonly defectComment: string, public readonly collapsibleState: vscode.TreeItemCollapsibleState) {
    super(defectName, collapsibleState);
    this.contextValue = `Project ${Math.ceil(Math.random() * 1000)}`;

    if (this.label != "TODO" && this.label != "FINISHED" && this.label != "STATISTICS") {
      this.description = `Project ${Math.ceil(Math.random() * 1000)}`;

      let tooltipContent = new vscode.MarkdownString();
      tooltipContent.appendMarkdown(`**${defectName}** ${defectLevel}\n\n`);
      tooltipContent.appendMarkdown(`- **Status:** ${defectStatus}\n`);
      tooltipContent.appendMarkdown(`- **Defect Type:** ${defectType}\n`);
      tooltipContent.appendMarkdown(`---\n\n`);
      tooltipContent.appendMarkdown(`**Defect Detail:**\n${defectDetail}\n\n`);
      tooltipContent.appendMarkdown(`---\n\n`);
      tooltipContent.appendMarkdown(`**Defect Comments:**\n${defectComment}\n`);

      this.tooltip = tooltipContent;
      this.tooltip.isTrusted = true;

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
      var childrenList = [];
      console.log("array length:");
      console.log(GlobalState.defectInfoArray.length)
      for (let index = 0; index < GlobalState.defectInfoArray.length; index++) {
        //console.log(GlobalState.defectInfoArray[index].isToDo)
        console.log(GlobalState.defectInfoArray[index]);
        console.log(element.label)
        
        if (GlobalState.defectInfoArray[index].isToDo == element.label) {
          let emoji: string = selectEmoji(GlobalState.defectInfoArray[index].defectLevel);
          var item = new SideBarEntryItem(
            GlobalState.defectInfoArray[index].defectName,
            GlobalState.defectInfoArray[index].defectStatus,
            GlobalState.defectInfoArray[index].defectType,
            emoji,
            GlobalState.defectInfoArray[index].defectDetail,
            GlobalState.defectInfoArray[index].defectComment,
            vscode.TreeItemCollapsibleState.None
          )
          item.command = {
            command: 'dashboard.openChild', 
            title: "asd",
            arguments: [GlobalState.defectInfoArray[index].defectDetail], 
          }
          childrenList[index] = item
        }
      }
      return childrenList
    } else {
      // Create root tags
      return [
        new SideBarEntryItem(
          'TODO',
          "null",
          "null",
          "null",
          "null",
          "null",
          vscode.TreeItemCollapsibleState.Expanded
        ),
        new SideBarEntryItem(
          'FINISHED',
          "null",
          "null",
          "null",
          "null",
          "null",
          vscode.TreeItemCollapsibleState.Expanded
        ),
        new SideBarEntryItem(
          'STATISTICS',
          "null",
          "null",
          "null",
          "null",
          "null",
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

  vscode.commands.registerCommand('dashboard.showContextMenu', (item: SideBarEntryItem) => {
    // Handle the context menu logic using the item information
    // You can access item.defectName, item.defectStatus, etc.
    // Implement your context menu functionality here
    console.log("asd");
    // Show a context menu
    vscode.window.showInformationMessage(`Context menu for: ${item.defectName}`);
  });
  
};
