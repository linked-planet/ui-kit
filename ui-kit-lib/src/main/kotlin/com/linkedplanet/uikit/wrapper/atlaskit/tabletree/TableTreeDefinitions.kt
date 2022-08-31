/**
 * Copyright 2022 linked-planet GmbH.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.linkedplanet.uikit.wrapper.atlaskit.tabletree

/**
 * Use this interface to build a tree that is consumable by the TableTree component.
 */
interface TableTreeItem {
    val id: String
    val children: Array<TableTreeItem>?
    val hasChildren: Boolean
}

/**
 * The Basic use case requires the content property.
 * It is required by the TableTreeProps.items
 * The more advanced use case allows you to set TTRows items that do not require the content property.
 */
interface TableTreeBasicUseCaseItem<ContentType> : TableTreeItem {
    val content: ContentType
}
