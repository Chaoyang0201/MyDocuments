private fun getUsesPermission(packageName: String) {

        val packageManager = this.packageManager
        val packageInfo = packageManager.getPackageInfo(packageName, PackageManager.GET_PERMISSIONS)
        val usesPermissionsArray = packageInfo.requestedPermissions
        val permissions = packageInfo.permissions

        System.out.println("packageName =" + packageName)
        System.out.println("AppName =" + packageInfo.applicationInfo.name)
        System.out.println("usesPermissionsArray Number =" + usesPermissionsArray.size)
//
        System.out.println("permissions Number =" + permissions.size)

        for (i in 0 until usesPermissionsArray.size) {

            //得到每个权限的名字,如:android.permission.INTERNET
            val usesPermissionName = usesPermissionsArray[i]
            Log.d("TAG", "usesPermissionName=" + usesPermissionName)

            //通过usesPermissionName获取该权限的详细信息
            val permissionInfo: PermissionInfo = packageManager.getPermissionInfo(usesPermissionName, 0)
            try {
                //获得该权限属于哪个权限组,如:网络通信

                val permissionGroupInfo = packageManager.getPermissionGroupInfo(permissionInfo.group, 0)
                System.out.println("permissionGroup=" + permissionGroupInfo.loadLabel(packageManager).toString())
            }catch (e:PackageManager.NameNotFoundException){
                System.out.println("unknown permission")

            }catch (e:Exception){
                e.printStackTrace()
            }



            //获取该权限的标签信息,比如:完全的网络访问权限
            val permissionLabel = permissionInfo.loadLabel(packageManager).toString()
            System.out.println("permissionLabel=" + permissionLabel)

            //获取该权限的详细描述信息,比如:允许该应用创建网络套接字和使用自定义网络协议
            //浏览器和其他某些应用提供了向互联网发送数据的途径,因此应用无需该权限即可向互联网发送数据.
            val description = permissionInfo.loadDescription(packageManager)
            if (description != null) {
                val permissionDescription = description.toString()
                System.out.println("permissionDescription=" + permissionDescription)
                System.out.println("===========================================")
            }
        }


    }
